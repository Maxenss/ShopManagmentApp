package com.company.Model;

public class Product {
    public final static String CATEGORY_SPORTS_PANTS = "Sports pants";
    public final static String CATEGORY_JEANS = "Jeans";
    public final static String CATEGORY_PANTS = "Pants";
    public final static String CATEGORY_T_Shirts = "T-shirts";
    public final static String CATEGORY_SHIRTS = "Shirts";
    public final static String CATEGORY_SWEATERS = "Sweaters";
    public final static String CATEGORY_JACKETS = "Jackets";

    private String mName;
    private int mId;
    private String mCategory;
    private int mCount;
    private double mCost;
    String mBranch;

    public Product(String mName, int id,
                   String mCategory,
                   int mCount,
                   double mCost,
                   String mBranch) {
        this.mName = mName;
        this.mCategory = mCategory;
        this.mCount = mCount;
        this.mCost = mCost;
        this.mBranch = mBranch;
        this.mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int mCount) {
        this.mCount = mCount;
    }

    public double getCost() {
        return mCost;
    }

    public void setCost(double mCost) {
        this.mCost = mCost;
    }

    public String getBranch() {
        return mBranch;
    }

    public void setBranch(String mBranch) {
        this.mBranch = mBranch;
    }
}
