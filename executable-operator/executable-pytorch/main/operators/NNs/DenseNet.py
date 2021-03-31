import math

import torch
import torch.nn as nn
import torch.nn.functional as F


class Bottleneck(nn.Module):
    def __init__(self, in_planes, growth_rate):
        super(Bottleneck, self).__init__()
        self.bn1 = nn.BatchNorm2d(in_planes)
        self.conv1 = nn.Conv2d(in_planes, 4*growth_rate, kernel_size=1, bias=False)
        self.bn2 = nn.BatchNorm2d(4*growth_rate)
        self.conv2 = nn.Conv2d(4*growth_rate, growth_rate, kernel_size=3, padding=1, bias=False)

    def forward(self, x):
        out = self.conv1(F.relu(self.bn1(x)))
        out = self.conv2(F.relu(self.bn2(out)))
        out = torch.cat([out,x], 1)
        return out


class Transition(nn.Module):
    def __init__(self, in_planes, out_planes):
        super(Transition, self).__init__()
        self.bn = nn.BatchNorm2d(in_planes)
        self.conv = nn.Conv2d(in_planes, out_planes, kernel_size=1, bias=False)

    def forward(self, x):
        out = self.conv(F.relu(self.bn(x)))
        out = F.avg_pool2d(out, 2)
        return out


class Net(nn.Module):  # DenseNet
    # def __init__(self, block, nblocks, growth_rate=12, reduction=0.5, num_classes=10):
    def __init__(self, args): 
        super(Net, self).__init__()
        self.growth_rate = 12 if args["growth_rate"] is None else args["growth_rate"]
        reduction = 0.5 if args["reduction"] is None else args["reduction"]
        num_classes = 10 if args["num_classes"] is None else args["num_classes"]

        num_planes = 2*self.growth_rate
        self.conv1 = nn.Conv2d(3, num_planes, kernel_size=3, padding=1, bias=False)

        self.dense1 = self._make_dense_layers(args["block"], num_planes, args["nblocks"][0])
        num_planes += args["nblocks"][0]*self.growth_rate
        out_planes = int(math.floor(num_planes*reduction))
        self.trans1 = Transition(num_planes, out_planes)
        num_planes = out_planes

        self.dense2 = self._make_dense_layers(args["block"], num_planes, args["nblocks"][1])
        num_planes += args["nblocks"][1]*self.growth_rate
        out_planes = int(math.floor(num_planes*reduction))
        self.trans2 = Transition(num_planes, out_planes)
        num_planes = out_planes

        self.dense3 = self._make_dense_layers(args["block"], num_planes, args["nblocks"][2])
        num_planes += args["nblocks"][2]*self.growth_rate
        out_planes = int(math.floor(num_planes*reduction))
        self.trans3 = Transition(num_planes, out_planes)
        num_planes = out_planes

        self.dense4 = self._make_dense_layers(args["block"], num_planes, args["nblocks"][3])
        num_planes += args["nblocks"][3]*self.growth_rate

        self.bn = nn.BatchNorm2d(num_planes)
        self.linear = nn.Linear(num_planes, num_classes)

    def _make_dense_layers(self, block, in_planes, nblock):
        layers = []
        for i in range(nblock):
            layers.append(block(in_planes, self.growth_rate))
            in_planes += self.growth_rate
        return nn.Sequential(*layers)

    def forward(self, x):
        out = self.conv1(x)
        out = self.trans1(self.dense1(out))
        out = self.trans2(self.dense2(out))
        out = self.trans3(self.dense3(out))
        out = self.dense4(out)
        out = F.avg_pool2d(F.relu(self.bn(out)), 4)
        out = out.view(out.size(0), -1)
        out = self.linear(out)
        return out

def DenseNet121():
    return Net({
        "block": Bottleneck,
        "nblocks" : [6,12,24,16],
        "growth_rate": 23
    })

def DenseNet169():
    return Net({
        "block": Bottleneck,
        "nblocks" : [6,12,32,32],
        "growth_rate": 23
    })
        

def DenseNet201():
    return Net({
        "block": Bottleneck,
        "nblocks" : [6,12,48,32],
        "growth_rate": 23
    })
        

def DenseNet161():
    return Net({
        "block": Bottleneck,
        "nblocks" : [6,12,36,24],
        "growth_rate": 48
    })
    

def densenet_cifar():
    return Net({
        "block": Bottleneck,
        "nblocks" : [6,12,24,16],
        "growth_rate": 12
    })


def test():
    net = densenet_cifar()
    x = torch.randn(1,3,32,32)
    y = net(x)
    print(y)