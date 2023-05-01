from tkinter import *

root = Tk()
root.title("제목 없음 - Windows 메모장")
root.geometry("640x480")
root.resizable(1,1)

scrollbar = Scrollbar(root)

txt = Text(root, yscrollcommand=scrollbar.set)
scrollbar.pack(side="right", fill="y")
txt.pack(side="left",fill="both",expand=True)


def create_new_file() :
    my_note = open("gui_basic/mynote.txt","w",encoding="UTF-8")    
    my_note.write(txt.get("1.0",END))
    my_note.close()

def load_file() :
    my_note = open("gui_basic/mynote.txt","r",encoding="UTF-8")
    txt.delete("1.0",END)
    data = my_note.read()
    txt.insert(END, data)
    my_note.close()


menu = Menu(root)
menu_file = Menu(menu, tearoff=0)
menu_file.add_command(label="열기",command=load_file)
menu_file.add_command(label="저장",command=create_new_file)
menu_file.add_separator()
menu_file.add_command(label="끝내기",command=root.quit)
menu.add_cascade(label="파일", menu=menu_file)
menu.add_cascade(label="편집")
menu.add_cascade(label="서식")
menu.add_cascade(label="보기")
menu.add_cascade(label="도움말")

root.config(menu=menu)
scrollbar.config(command=txt.yview)

root.mainloop()
