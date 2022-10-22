package dodizhu3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class PoKerGame {
        //1,准备牌  因为每次玩时，只需要一副牌，所以放在构造方法的外面。
        //定义集合，存储牌
        static ArrayList<String> list=new ArrayList();
        //创建一个集合，用来添加牌的价值
        static HashMap<String,Integer> hm=new HashMap<>();
        static {
            //因为牌数固定，所以选择用数组存储
            String[] color={"♦","♣","♥","♠"};
            String[] number={"3","4","5","6","7","8","9","10","J","Q","K","1","2"};

            //遍历，将颜色和数量拼接在一起
            //遍历颜色
            for (String c : color) {
                //遍历数字
                for (String n : number) {
                    list.add(c+n);
                }
            }
            list.add(" 大王");
            list.add(" 小王");
            //指定牌的价值
            //牌上的数组到Map集合中判断是否存在
            //存在，获取价值
            //不存在，本身的数字就是价值
            hm.put("J",11);
            hm.put("Q",12);
            hm.put("K",13);
            hm.put("1",14);
            hm.put("2",15);
            hm.put("小王",16);
            hm.put("大王",17);



        }
        public PoKerGame(){
            //2.洗牌
            Collections.shuffle(list);
            //3.发牌
            //定义4个list集合，用于存储底牌和三位玩家的牌
            ArrayList<String> lord=new ArrayList<>();
            ArrayList<String> player1=new ArrayList<>();
            ArrayList<String> player2=new ArrayList<>();
            ArrayList<String> player3=new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String poker = list.get(i);
                if (i<=2){
                    lord.add(poker);
                }
                if(i%3==0){
                    player1.add(poker);
                }else if(i%3==1){
                    player2.add(poker);
                }else{
                    player3.add(poker);
                }
            }
            //排序
            order(lord);
            order(player1);
            order(player2);
            order(player3);

            //4.看牌
            lookPoker("底牌",lord);
            lookPoker("张三",player1);
            lookPoker("李四",player2);
            lookPoker("王五",player3);
        }
        //根据牌的价值排序
        // 参数：集合
        public void order(ArrayList<String> list){
            Collections.sort(list, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    //o1:表示当前要插入到有序序列中的牌
                    //o2;表示已经存在有序序列中的牌
                    //正数：o1大，插入到后面
                    //负数：o2大，插入到前面
                    //0:o1的数字跟o2的数字是一样的，需要按照花色再次排序

                    //1.计算o1的花色和价值
                    String color1=o1.substring(0,1);
                    int value1=getValue(o1);
                    //2.计算o2的花色和价值
                    String color2=o2.substring(0,1);
                    int value2=getValue(o2);
                    //3.比较o1和o2的价值
                    int i = value1 - value2;
                    return i==0?color1.compareTo(color2):i;
                }
            });
        }
        //计算牌的价值
       //参数:牌
      //返回值：价值
        public int getValue(String poker){
            //获取牌上的数字
            String number=poker.substring(1);
            //拿着数字到Map集合中判断是否存在。若存在，则获取价值，若不存在，则类型转换
            if (hm.containsKey(number)){
                //若存在，则获取价值
                return  hm.get(number);
            }else {
                //若不存在，则类型转换
               return Integer.parseInt(number);
            }
        }




        public void lookPoker(String name,ArrayList<String> list){
            System.out.print(name+":");
            for (String poker:list){
                System.out.print(poker+" ");
            }
            System.out.println();

        }


}
