package com.fzu.teamwork.view;

import java.util.List;

public class Page {
    private int pageIndex;//当前页号
    private int count;//本页要展示的最大条数
    private List<Integer> buttonList;//最近的5个页号的页码序列
    private boolean hasPrivious;//是否有上一页
    private boolean hasNext;//是否有下一页
    private int pageNum;//总页数
}
