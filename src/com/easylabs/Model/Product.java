package com.easylabs.Model;

public class Product {
    public final static String CATEGORY_SPORTS_PANTS = "Sports pants";
    public final static String CATEGORY_JEANS = "Jeans";
    public final static String CATEGORY_PANTS = "Pants";
    public final static String CATEGORY_T_Shirts = "T-shirts";
    public final static String CATEGORY_SHIRTS = "Shirts";
    public final static String CATEGORY_SWEATERS = "Sweaters";
    public final static String CATEGORY_JACKETS = "Jackets";

    private String mName;
    private String mCategory;
    private int mCount;
    private int mCost;
    String mFiliation;

    public Product(String mName,
                   String mCategory,
                   int mCount,
                   int mCost,
                   String mFiliation) {
        this.mName = mName;
        this.mCategory = mCategory;
        this.mCount = mCount;
        this.mCost = mCost;
        this.mFiliation = mFiliation;
    }
}
