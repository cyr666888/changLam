package com.sangeng1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Author> authors = getAuthors();
      /*  authors.stream()
                .distinct()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()<18;
                    }
                })
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println(author.getName());
                    }
                });*/
        //test01(authors);//Ctrl+Alt+m 生成

        //  test02();

//        test03();
        //   test04();

        // test05();
        //  test07();
        //test08();
        /*map只能把一个对象转换成另一个对象来作为流中的元素，而flatmap可以把一个对象转换成多个对象作为流中的元素*/
       // test11();
        //test12();
        //test15();
       // test16();
        test17();
    }

    private static void test17() {
        List<Author> authors = getAuthors();
    /*  *//*  Integer reduce = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (integer, integer2) -> integer + integer2);
        System.out.println(reduce);*//*
        //        使用reduce求所有作者中年龄的最小值
        Optional<Integer> minOptional = authors.stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? element : result);
        minOptional.ifPresent(age-> System.out.println(age));*/

       authors.stream().map(author -> author.getAge())
               .map(age->String.valueOf(age));

    }
    /*你不可能每种可能的空指针异常都能想得到吧，也不可能每调用一个方法都要if验证吧，但你可以每个方法都使用option的ifpresent来调用，相当于添加了一层过滤*/

    private static void test16() {
       /* List<Author> authors=getAuthors();
        Optional<Author> any = authors.stream()
                .findAny();
        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));
        */

        //        获取任意一个年龄大于18的作家，如果存在就输出他的名字
        List<Author> authors = getAuthors();
        Optional<Author> optionalAuthor = authors.stream()
                .filter(author -> author.getAge()>18)
                .findAny();

        optionalAuthor.ifPresent(author -> System.out.println(author.getName()));

    }

    private static void test15() {
        List<Author> authors=getAuthors();
       /* List<String> collect = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());*/
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .forEach(books -> System.out.println(books));

    }

    private static void test12() {
        List<Author> authors=getAuthors();
       /* Optional<Book> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .max((o1, o2) -> o1.getScore() - o2.getScore());
        System.out.println(max.get().getScore());*/
        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getScore())
                .max(((o1, o2) -> o1 - o2));
        System.out.println(max.get());

    }

    private static void test11() {
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))//split相当于按照这个进行分隔得到了字符串数组 在转为流对象 因为返回值类型是流对象
                .distinct()
                .forEach(c -> System.out.println(c));
    }

    private static void test07() {
        //	可以对流中的元素进行排序。
        List<Author> authors = getAuthors();
      /*  authors.stream()
                .distinct()
                .sorted()//用不带参数的需要实现接口
                .forEach(author -> System.out.println(author.getAge()));*/

    }

    private static void test05() {
        List<Author> authors = getAuthors();
       /* authors.stream()
                .map(new Function<Author, String>() {
                    @Override
                    public String apply(Author author) {

                        return author.getName();
                    }
                }).forEach(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        System.out.println(s);
                    }
                });*/



       /* authors.stream()
                .map(author -> author.getName())	//可以把对流中的元素进行计算或转换。
                .forEach(name -> System.out.println(name));*/
        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(age -> System.out.println(age));
    }

    private static void test04() {
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .filter(author -> author.getName().length() > 1)
                .forEach(author -> System.out.println(author.getName()));

    }

    private static void test03() {
        Map<String, Integer> map = new HashMap<>();
        map.put("蜡笔小新", 19);
        map.put("黑子", 17);
        map.put("日向翔阳", 16);

        Stream<Map.Entry<String, Integer>> stream = map.entrySet().stream();
    }

    private static void test02() {
        Integer[] arr = {1, 2, 3, 4, 5};
        Stream<Integer> stream = Arrays.stream(arr);//相当于传入一个数组就会返回一个流对象


        Stream<Integer> stream2 = Stream.of(arr);//Stream的of方法也可以转为流
    }

    private static void test01(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(author -> author.getAge() < 18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
}
