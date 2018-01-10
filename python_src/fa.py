# -*- coding: utf-8 -*-
"""
对java生成的数据进行处理
"""
import numpy as np
import csv
import matplotlib.pyplot as plt
import matplotlib.animation as animation


class Firefly(object):
    def __init__(self):
        self.data = self.read_csv()
        self.fig, self.ax = plt.subplots()

        for i in range(0, 34):
            self.draw()

        #self.x = np.arange(0, 2 * np.pi, 0.01)
        #self.line, = self.ax.plot(self.x, np.sin(self.x))
        #self.ani = animation.FuncAnimation(
        #    fig=self.fig, func=self.update, frames=100, init_func=self.init, interval=200, blit=True)
        #self.fig, self.ax = plt.subplots()
        #self.ani = animation.FuncAnimation(self.fig, self.update, interval=2000,
        #                                   init_func=self.draw, blit=True)

    def read_csv(self):
        "读取CSV里的数据"
        with open('datacsv') as f:
            f_csv = csv.reader(f)
            for row in f_csv:
                data = list(map(float, row))
                yield data

    def get_data(self):
        data = next(self.data)
        return data

    def update(self):
        "动态更新数据用"
        data = self.get_data()
        n = len(data)
        n = int(n / 3)
        init_X = data[:n]
        init_Y = data[n:2 * n]
        init_R = data[2 * n:]
        X = np.array(init_X)
        Y = np.array(init_Y)
        T = np.arctan2(Y, X)
        area = np.array(init_R) * 50
        self.scatters.set_offsets([(X[:], Y[:])])
        self.scatters._sizes = area[:]
        self.scatters.set_array([T[:]])
        return self.scatters,

    def draw(self):
        data = self.get_data()
        n = len(data)
        n = int(n / 3)
        init_X = data[:n]
        init_Y = data[n:2 * n]
        init_R = data[2 * n:]
        X = np.array(init_X) / 90
        Y = np.array(init_Y) / 90
        T = np.arctan2(Y, X)
        area = np.array(init_R) * 25
        ## marker="+"
        plt.scatter(X, Y, s=area, c=T, alpha=0.5)

        plt.xlim((-5, 15))
        plt.ylim((-5, 15))
        plt.show()

    def animations(self, i):
        pass

    def init(self):
        "动态图片 初始化的点坐标设置"
        data = self.get_data()
        n = len(data)
        n = int(n / 3)
        init_X = data[:n]
        init_Y = data[n:2 * n]
        init_R = data[2 * n:]
        X = np.array(init_X)
        Y = np.array(init_Y)
        T = np.arctan2(Y, X)
        area = np.array(init_R) * 40
        ## marker="+"
        self.scatters = self.ax.scatter(X, Y, s=area, c=T, alpha=0.5)
        self.ax.axis([-30, 30, -30, 30])
        return self.scatters,

    def show(self):
        plt.show()


if __name__ == '__main__':
    Fa = Firefly()
    Fa.show()