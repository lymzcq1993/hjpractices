package hujian.jvm.classloaderTest;

/**
 * @Classname StringPools
 * @Description String类的各种例子
 * @Date 2020/9/11 23:30
 * @Created by 35918
 */
public class StringPools {
    public static void main(String[] args) {
        //new String("a")会在常量池和堆中创建对象,而new String()与字面量相加不会再编译的时间确定，只能再运行时确定
        // ，“ab”字面量只会在常量池中创建
        //非new String（）或者StringBuilder/StringBuffer的字面量相加都是指向同一个常量池
        String s1 = "ab";
        String s2 = new String("a")+"b";
        System.out.println(s1 == s2);

        //方法在运行期间拼接才能确定值，所以会动态分配堆中的地址
        String s3 = "ab";
        String s4 ="a" +getB();
        System.out.println(s3 == s4);

        //JDK1.6 intern如果在常量池中没有会直接创建一个新的对象
        //JDK1.7 1.8intern会找堆中有没有该字符串，有就直接指向字符串
        //第一个例子中在常量池中没有生成hello，所以intern直接指向了s5本身，
        //第二个例子在常量池中生成了hello，所以intern指向常量池的hello
        String s5 = new StringBuilder("hel").append("lo").toString();
        System.out.println(s5 == s5.intern());

        String s6 = new StringBuilder("hello").toString();
        System.out.println(s6 == s6.intern());

        //在编译中a+b+c是用apeend拼接，而s7编译阶段直接优化成了s7 = “abc"
        String s7 = "a" + "b" +"c";
        String a = "a";
        String b = "b";
        String c = "c";
        String s8 = a + b + c;
        //false
        System.out.println(s7 == s8);
    }

    static String getB(){
        return "b";
    }
}