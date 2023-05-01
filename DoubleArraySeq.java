import java.util.Arrays;

public class DoubleArraySeq implements Cloneable {
    double[] data;
    int currentelement = -1; // currentelement가 없을시 -1
    int manyitem;

    public static void main(String[] args) {
        DoubleArraySeq seq1 = new DoubleArraySeq(20);
        System.out.print("seq1 isCurrent : ");
        System.out.println(seq1.isCurrent());
        seq1.addAfter(13);
        seq1.addAfter(25.3);
        seq1.addAfter(68);
        seq1.addBefore(9.12);
        seq1.addBefore(-1.13);
        seq1.addBefore(-6);
        System.out.print("seq1 : ");
        System.out.println(Arrays.toString(seq1.data));// capacity를 보여주기 위해 Array전체출력을하였습니다.
        System.out.print("seq1 isCurrent : ");
        System.out.println(seq1.isCurrent());
        DoubleArraySeq seq2 = new DoubleArraySeq();
        seq2.addAfter(5);
        seq2.addAfter(14);
        seq2.addAfter(3.5);
        seq2.addAfter(-7);
        seq2.addAfter(-8.24);
        seq2.addAfter(3);
        System.out.print("seq2 : ");
        System.out.println(Arrays.toString(seq2.data));
        seq2.ensureCapacity(15);
        System.out.print("seq2 after ensureCapacity : ");
        System.out.println(Arrays.toString(seq2.data));
        seq1.addAll(seq2);
        System.out.print("seq1 after addAll : ");
        System.out.println(Arrays.toString(seq1.data));
        seq1.start();
        System.out.print("seq1 current element after start : ");
        System.out.println(seq1.getCurrent());
        seq1.advance();
        System.out.print("seq1 current element after advance : ");
        System.out.println(seq1.getCurrent());
        seq1.advance();
        System.out.print("seq1 current element after advance : ");
        System.out.println(seq1.getCurrent());
        seq1.removeCurrent();
        System.out.print("seq1 after removeCurrent : ");
        System.out.println(Arrays.toString(seq1.data));
        System.out.print("seq1 capacity : ");
        System.out.println(seq1.getCapacity());
        System.out.print("seq1 size : ");
        System.out.println(seq1.size());
        seq1.trimToSize();
        System.out.print("seq1 after trimToSize : ");
        System.out.println(Arrays.toString(seq1.data));
        DoubleArraySeq seq3 = seq1.clone();
        System.out.print("clone of seq1 : ");
        System.out.println(Arrays.toString(seq3.data));
        DoubleArraySeq seq4 = concatenation(seq1, seq2);
        System.out.print("concatenation of seq1 and seq2 : ");
        System.out.println(Arrays.toString(seq4.data));
    }

    public DoubleArraySeq(int initialcapacity) {
        if (initialcapacity < 0) {// initialcapacity가 음수일 시 ILllegalArgumentException 을 throw한다.
            throw new IllegalArgumentException("initialcapacity is negative.");
        }
        try {
            data = new double[initialcapacity];
        } catch (OutOfMemoryError e) {// 사용 가능한 memory공간이 부족할 경우 OutOfMemoryError 을 throw 한다.
            throw new OutOfMemoryError("insufficient memory for: new double[initialCapacity].");
        }
        manyitem = 0;
    }

    public DoubleArraySeq() { // capacity를 지정하지 않고 DoubleArraySeq를 생성시 10을 초기값으로 설정
        data = new double[10];
        manyitem = 0;
    }

    // minimumCapacity만큼 DoubleArraySeq의 capacity를 확장한다.
    // 만약 minimumCapacity값이 Integer.MAX_VALUE를 넘어서는 값이 들어오면 ArithmeticException을
    // throw한다.
    public void ensureCapacity(int minimumCapacity) {
        double biggerdata[];
        if (minimumCapacity > Integer.MAX_VALUE) {
            throw new ArithmeticException("The capacity beyond Integer.MAX_VALUE.");
        }
        try {
            biggerdata = new double[minimumCapacity];
            System.arraycopy(data, 0, biggerdata, 0, manyitem);
            data = biggerdata;
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("insufficient memory for: new double[initialCapacity].");
        }
    }

    // currentelement바로 다음에 element추가
    // 만약 DoubleArraySeq가 다 차있을 경우 ensurecapacity()로 크기를 늘린다.
    // currentelement전까지 하나씩 뒤로 민 다음 currentelement+1에 element를 추가한다.
    // 이후 추가한 원소를 currentelement로 바꾸고 manyitem도 +1을 해준다.
    // 만약 currentelement가 없다면 DoubleArraySeq의 가장 뒤에 element를 추가한다.
    public void addAfter(double element) {
        if (data.length == manyitem) {
            ensureCapacity((manyitem + 1) * 2);
        }
        try {
            if (isCurrent()) {
                for (int i = manyitem; i > currentelement + 1; i--) {
                    data[i] = data[i - 1];
                }
                data[currentelement + 1] = element;
                currentelement++;
                manyitem++;
            } else {
                data[manyitem] = element;
                manyitem++;
                currentelement = manyitem - 1;
            }
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("insufficient memory to increase the size of this sequence.");
        }
    }

    // currentelement바로 앞에 element를 추가
    // 만약 DoubleArraySeq가 다 차있을 경우 ensurecapacity()로 크기를 늘린다.
    // Sequence의 끝에서부터 currentelement까지 뒤로 한칸씩 밀고
    // 기존의 currentelement 위치에 element를 추가한다.
    // 만약 currentelement가 없다면 Seq의 제일 앞에 element를 추가한다.
    public void addBefore(double element) {
        if (data.length == manyitem) {
            ensureCapacity((manyitem + 1) * 2);
        }
        try {
            if (isCurrent()) {
                for (int i = manyitem; i > currentelement; i--) {
                    data[i] = data[i - 1];
                }
                data[currentelement] = element;
                manyitem++;
            } else {
                for (int i = manyitem; i > 0; i++) {
                    data[i] = data[i - 1];
                }
                data[0] = element;
                currentelement = 0;
                manyitem++;
            }
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("insufficient memory to increase the size of this sequence.");
        }
    }

    // 또다른 DoubleArraySeq를 DoubleArraySeq 뒤에 붙인다.
    // 만약 addend가 null이라면 NullPointerException을 throw한다.
    // manytime과 addend의 manyitem을 더한 값보다 크게 ensurecapacity를 해준다.
    // 이후 data의 뒤에 addend의 data를 붙여넣는다.
    // 만약 memory 공간이 불충분할경우 OutOfMemoryError 를 throw한다.
    public void addAll(DoubleArraySeq addend) {
        if (addend == null) {
            throw new NullPointerException("addend is null.");
        }
        try {
            ensureCapacity(manyitem + addend.manyitem + 1);
            System.arraycopy(addend.data, 0, data, manyitem, addend.manyitem);
            manyitem = manyitem + addend.manyitem;
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("insufficient memory to increase the capacity of this sequence.");
        }
    }

    // currentelement의 다음 element를 currentelement로 가르킨다.
    // 만약 가장
    // 만약 isCurrent값이 False라면 IllegalStateException을 throw한다.
    public void advance() {
        if (isCurrent()) {
            if (currentelement == manyitem - 1) {
                currentelement = -1;
            } else {
                currentelement++;
            }
        } else {
            throw new IllegalStateException("there is no current element, so advance may not be called.");
        }
    }

    // currentelement의 유무를 확인한다.
    // 만약 currentelement가 없을경우(-1) false를 있다면 true를 return한다.
    public boolean isCurrent() {
        if (currentelement == -1) {
            return false;
        } else {
            return true;
        }
    }

    // clone 생성
    // 만약 implements Cloneable이 없을 경우 CloneNotSupportedException
    // DoubleArraySeq를 복사한다.
    public DoubleArraySeq clone() {
        DoubleArraySeq answer;

        try {
            answer = (DoubleArraySeq) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        } catch (OutOfMemoryError e2) {
            throw new OutOfMemoryError("insufficient memory for creating the clone.");
        }
        answer.data = data.clone();
        return answer;
    }

    // 2개의 DoubleArraySeq를 합친 새로운 DoubleArraySeq를 만든다.
    // 만약 입력받은 DoubleArraySeq중 하나라도 null이라면 NullPointerException을 throw한다.
    // s1뒤에 s2를 붙이고 manyitem은 s1과 s2의 manyitem을 더한 값을 갖는다.
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("one of the arguments is null.");
        }
        try {
            DoubleArraySeq answer = new DoubleArraySeq(s1.manyitem + s2.manyitem);
            System.arraycopy(s1.data, 0, answer.data, 0, s1.manyitem);
            System.arraycopy(s2.data, 0, answer.data, s1.manyitem, s2.manyitem);
            answer.manyitem = s1.manyitem + s2.manyitem;
            return answer;
        } catch (OutOfMemoryError e) {
            throw new OutOfMemoryError("insufficient memory for the new sequence.");
        }
    }

    // DoubleArraySeq의 capacity값을 return한다.
    public int getCapacity() {
        return data.length;
    }

    // DoubleArraySeq의 현재 가리키고 있는 currentelement를 return한다.
    // 만약 isCurrent가 false면 IllegalStateException를 throw한다.
    public double getCurrent() {
        if (isCurrent()) {
            return data[currentelement];
        } else {
            throw new IllegalStateException("there is no current element.");
        }
    }

    // currentelement를 삭제한다.
    // 만약 isCurrent가 false면 IllegalStateException를 throw한다.
    // 그렇지 않다면 currentelement부터 끝까지 원소를 당겨온다.
    // 이후 마지막 원소가 동일한 것이 2개가 되니 끝에 것을 잘라준다.
    public void removeCurrent() {
        if (isCurrent()) {
            for (int i = currentelement; i < manyitem - 1; i++) {
                data[i] = data[i + 1];
            }
            manyitem--;
            if (currentelement == manyitem) {
                currentelement = -1;
            }
            DoubleArraySeq temp = new DoubleArraySeq(data.length);
            System.arraycopy(data, 0, temp.data, 0, manyitem);
            data = temp.data;
        } else {
            throw new IllegalStateException("there is no current element.");
        }
    }

    // DoubleArraySeq의 size(manyitem)을 return한다.
    public int size() {
        return manyitem;
    }

    // DoubleArraySeq의 가장 앞에 있는 원소를 currentelement로 지정한다.
    // 만약 DoubleArraySeq가 비어있다면 currentelement는 없다.
    public void start() {
        if (manyitem == 0) {
            currentelement = -1;
        } else {
            currentelement = 0;
        }
    }

    // DoubleArraySeq의 capacity를 manyitem만큼만 할당한다.
    public void trimToSize(){
        try {
            double[] answer = new double[manyitem];
            System.arraycopy(data, 0, answer, 0, manyitem);   
            data = answer;
        }catch(OutOfMemoryError e){
            throw new OutOfMemoryError("insufficient memory for altering the capacity.");
        }
    }

}

