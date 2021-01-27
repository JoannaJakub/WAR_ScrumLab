package pl.coderslab.dao;

public class LastPlan {
    private String dayName;
    private String mealName;
    private String recipeName;
    private String recipeDescription;
    private String recipeId;

    public LastPlan(){

    }

    public LastPlan(String dayName, String mealName, String recipeName, String recipeDescription, String recipeId) {
        this.dayName = dayName;
        this.mealName = mealName;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeId = recipeId;
    }
    //
    public String getDayName() {
        return dayName;
    }

    public String getMealName() {
        return mealName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRecipeDescription() {
        return recipeDescription;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public String toString() {
        return "LastPlan{" +
                "dayName='" + dayName + '\'' +
                ", mealName='" + mealName + '\'' +
                ", recipeName='" + recipeName + '\'' +
                ", recipeDescription='" + recipeDescription + '\'' +
                ", recipeId='" + recipeId + '\'' +
                '}';
    }
}
