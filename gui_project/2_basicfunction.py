from tkinter import *
import tkinter.ttk as ttk
from tkinter import filedialog
import tkinter.messagebox as msgbox


root = Tk()
root.title("Nado GUI")

def add_file() :
    files = filedialog.askopenfilenames(title="이미지 파일을 선택하세요", filetypes=(("PNG 파일", "*.png"), ("모든 파일", "*.*")), initialdir=r"C:\Users\Minjun\Desktop\PythonStudy\pygame_project\images")
    #최초에 사용자가 지정한 경로를 보여줌
    for file in files :
        list_file.insert(END,file)

def delete_file() :
    print(list_file.curselection())
    for index in reversed(list_file.curselection()) :
        list_file.delete(index)

#저장 경로 (폴더)


def browse_dest_path():
    folder_selected = filedialog.askdirectory()
    if folder_selected == None:
        return
    txt_dest_path.delete(0,END)
    txt_dest_path.insert(0,folder_selected)

def start() :
    print("가로넓이 : ",cmb_width.get())
    print("간격  :",cmb_space.get())
    print("포멧 : ",cmb_format.get())
    #파일 목록 확인
    if list_file.size() == 0 :
        msgbox.showwarning("경고","이미지 파일을 추가하세요")
        return
    if len(txt_dest_path.get()) == 0 :
        msgbox.showwarning("경고", "저장 경로를 선택하세요")
        return

file_frame = Frame(root)
file_frame.pack(fill="x", padx=5, pady=5)
btn_add_file = Button(file_frame, padx=5, pady=5, width=12, text="파일 추가",command=add_file)
btn_add_file.pack(side="left")
btn_delete_file = Button(file_frame, padx=5, pady=5, width=12, text="선택 삭제",command=delete_file)
btn_delete_file.pack(side="right")

list_frame = Frame(root)
list_frame.pack(fill="both", padx=5, pady=5)

scrollbar = Scrollbar(list_frame)
scrollbar.pack(side="right", fill="y")

list_file = Listbox(list_frame, selectmode="extended",
                    height=15, yscrollcommand=scrollbar.set)
list_file.pack(side="left", fill="both", expand=True)
scrollbar.config(command=list_file.yview)

path_frame = LabelFrame(root, text="저장경로")
path_frame.pack(fill="x", padx=5, pady=5, ipady=5)

txt_dest_path = Entry(path_frame)
txt_dest_path.pack(side="left", fill="x", expand=True, padx=5, pady=5, ipady=4)

btn_dest_path = Button(path_frame, text="찾아보기", width=10,command=browse_dest_path)
btn_dest_path.pack(side="right", padx=5, pady=5)

option_frame = LabelFrame(root, text="옵션")
option_frame.pack(padx=5, pady=5, ipady=5)

#가로 놃이 옵션
lbl_width = Label(option_frame, text="가로넓이", width=9)
lbl_width.pack(side="left", padx=5, pady=5)

opt_width = ["원본유지", "1024", "800", "640"]
cmb_width = ttk.Combobox(option_frame, state="readonly",
                         values=opt_width, width=10)
cmb_width.current(0)
cmb_width.pack(side="left", padx=5, pady=5)

lbl_space = Label(option_frame, text="간격", width=9)
lbl_space.pack(side="left", padx=5, pady=5)

opt_space = ["없음", "좁게", "보통", "넓게"]
cmb_space = ttk.Combobox(option_frame, state="readonly",
                         values=opt_space, width=10)
cmb_space.current(0)
cmb_space.pack(side="left", padx=5, pady=5)

lbl_format = Label(option_frame, text="포멧", width=9)
lbl_format.pack(side="left", padx=5, pady=5)

opt_format = ["PNG", "JPG", "BMP"]
cmb_format = ttk.Combobox(
    option_frame, state="readonly", values=opt_format, width=10)
cmb_format.current(0)
cmb_format.pack(side="left", padx=5, pady=5)

#진행 상황 프로그레스바
frame_progress = LabelFrame(root, text="진행상황")
frame_progress.pack(fill="x", padx=5, pady=5, ipady=5)

p_var = DoubleVar()
progress_bar = ttk.Progressbar(frame_progress, maximum=100, variable=p_var)
progress_bar.pack(fill="x", padx=5, pady=5)

frame_run = Frame(root)
frame_run.pack(fill="x", padx=5, pady=5)

btn_close = Button(frame_run, padx=5, pady=5, text="닫기",
                   width=12, command=root.quit)
btn_close.pack(side="right", padx=5, pady=5)

btn_start = Button(frame_run, padx=5, pady=5, text="시작", width=12,command=start)
btn_start.pack(side="right", padx=5, pady=5)

root.resizable(False, False)
root.mainloop()
