package com.jisellemartins.themovie.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseMovie {
    private ArrayList<Results> results;
    private int page;
    private int total_results;
    //private ArrayList<Dates> dates;
    private int total_pages;




    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }



    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<Results> getResults() {
        return results;
    }
}
