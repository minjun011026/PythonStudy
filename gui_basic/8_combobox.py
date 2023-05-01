import tkinter.ttk as ttk
from tkinter import *

root = Tk()
root.title("Nado GUI")
root.geometry("640x480")

values = [str(i) + "일" for i in range(1,32)]
combobox = ttk.Combobox(root,height = 5, values=values)
combobox.pack()
combobox.set("카드 결제일")

readonlycombobox = ttk.Combobox(root, height=10, values=values,state = "readonly")
readonlycombobox.pack()
readonlycombobox.current(0)

def btncmd():
    print(combobox.get())
    print(readonlycombobox.get())


btn = Button(root, text="선택", command=btncmd)
btn.pack()
root.mainloop()
