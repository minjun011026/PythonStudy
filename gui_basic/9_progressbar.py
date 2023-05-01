import time
import tkinter.ttk as ttk
from tkinter import *

root = Tk()
root.title("Nado GUI")
root.geometry("640x480")

p_var2 = DoubleVar()
progressbar2 = ttk.Progressbar(root, maximum=100, length= 150,variable=p_var2)
progressbar2.pack()

def btncmd2() :
    for i in range(1,101) :
        time.sleep(0.01)
        p_var2.set(i)
        progressbar2.update()
        print(p_var2.get())

btn1 = Button(root, text="시작",command=btncmd2)
btn1.pack()
root.mainloop()
