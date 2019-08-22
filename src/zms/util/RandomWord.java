package zms.util;

/**
 * 生成随机验证码
 * @author 19448
 */
public class RandomWord {
    public static String getWord(int index){
        char[] ch=new char[index];
        for(int i=0;i<index;i++){
            char c1 = (char)(Math.random()*26+97);
            char c2=(char)(Math.random()*26+65);
            char n=(char) (Math.random()*10+48);
            char[] chars={c1,c2,n};
            char c=chars[(int)(Math.random()*3)];
            ch[i]=c;
        }
        return new String(ch);
    }

//    public static void main(String[] args) {
//        String word = getWord(8);
//        System.out.println(word);
//    }
}
