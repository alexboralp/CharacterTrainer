/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cc.images.ImageIconManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fileioutils.FDBFact;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import model.food.Food;
import model.food.FoodFact;
import model.food.FoodList;
import model.food.interfaces.IFood;
import model.utils.MainProperties;
import vista.editors.FoodEditor;
import vista.listeners.FoodBtnAdd;
import vista.listeners.FoodBtnImage;
import vista.listeners.FoodBtnNext;
import vista.listeners.FoodBtnPrevious;
import vista.listeners.FoodBtnRemove;
import vista.listeners.FoodBtnSave;

/**
 *
 * @author alexander
 */
public class FoodEditorAdmin implements IAdmin {
    
    private FoodEditor frmFoodEditor;
    private FoodList foodList;
    
    private int actualFood;
    
    public FoodEditorAdmin() {
        _init_();
    }

    public FoodEditorAdmin(FoodEditor frmFoodEditor) {
        this.frmFoodEditor = frmFoodEditor;
        _init_();
        _init_components_();
    }
    
    private void _init_() {
        foodList = new FoodList();
        /*foodList.add(new Food("Nombre de la comida", "Direccion de la comida", 5, 2));
        foodList.add(new Food("Nombre de la comida2", "Direccion de la comida2", 7, 1));*/
        
        loadFood();
    }
    
    private void _init_components_() {
        frmFoodEditor.btnSave.addActionListener(new FoodBtnSave(this));
        frmFoodEditor.btnPrevious.addActionListener(new FoodBtnPrevious(this));
        frmFoodEditor.btnNext.addActionListener(new FoodBtnNext(this));
        frmFoodEditor.btnImage.addActionListener(new FoodBtnImage(this));
        frmFoodEditor.btnAdd.addActionListener(new FoodBtnAdd(this));
        frmFoodEditor.btnDelete.addActionListener(new FoodBtnRemove(this));
        
        if (foodList.size() > 0) {
            GUIShowFood(foodList.getFirst());
            actualFood = 0;
        }
    }
    
    public void GUIShowFood(IFood food) {
        GUISetName(food.getName());
        GUISetPath(food.getPath());
        GUISetFullnesLevel(food.getFullnesLevel());
        GUISetLiquidsLevel(food.getLiquidsLevel());
        GUISetFoodIcon(ImageIconManager.getInstance().getImage(food.getPath()));
    }
    
    public void GUIShowIcon(ImageIcon foodIcon) {
        GUISetFoodIcon(foodIcon);
    }
    
    public void GUIDeleteFood() {
        GUISetName("");
        GUISetPath("");
        frmFoodEditor.txtFullnesLevel.setText("");
        frmFoodEditor.txtLiquidsLevel.setText("");
        GUISetFoodIcon(null);
    }
    
    public String GUIGetName() {
        return frmFoodEditor.txtName.getText();
    }
    
    public void GUISetName(String name) {
        frmFoodEditor.txtName.setText(name);
    }
    
    public String GUIGetPath() {
        return frmFoodEditor.txtImage.getText();
    }
    
    public void GUISetPath(String path) {
        frmFoodEditor.txtImage.setText(path);
    }
    
    public int GUIGetFullnesLevel() {
        return Integer.parseInt(frmFoodEditor.txtFullnesLevel.getText());
    }
    
    public void GUISetFullnesLevel(int fullnesLevel) {
        frmFoodEditor.txtFullnesLevel.setText(Integer.toString(fullnesLevel));
    }
    
    public int GUIGetLiquidsLevel() {
        return Integer.parseInt(frmFoodEditor.txtFullnesLevel.getText());
    }
    
    public void GUISetLiquidsLevel(int liquidsLevel) {
        frmFoodEditor.txtLiquidsLevel.setText(Integer.toString(liquidsLevel));
    }
    
    public ImageIcon GUIGetFoodIcon() {
        return (ImageIcon)frmFoodEditor.lblFoodImage.getIcon();
    }
    
    public void GUISetFoodIcon(ImageIcon icon) {
        frmFoodEditor.lblFoodImage.setIcon(icon);
    }
    
    public void addFood(IFood food) {
        foodList.add(food);
    }
    
    public void nextFood() {
        if (foodList.size() > 0) {
            actualFood++;
            if (actualFood < foodList.size()) {
                GUIShowFood(foodList.get(actualFood));
            } else {
                actualFood = foodList.size() - 1;
                GUIShowFood(foodList.get(actualFood));
            }
        } else {
            GUIShowFood(FoodFact.NULL_FOOD);
        }
    }
    
    public void previousFood() {
        if (foodList.size() > 0) {
            actualFood--;
            if (actualFood >= 0) {
                GUIShowFood(foodList.get(actualFood));
            } else {
                actualFood = 0;
                GUIShowFood(foodList.get(actualFood));
            }
        } else {
            GUIShowFood(FoodFact.NULL_FOOD);
        }
    }
    
    public void addFirst(IFood food) {
        foodList.addFirst(food);
    }
    
    public void addLast(IFood food) {
        foodList.addLast(food);
    }
    
    public void add(int index, IFood food) {
        foodList.add(index, food);
    }
    
    public void add(IFood food) {
        foodList.add(actualFood, food);
    }
    
    public void remove(int index) {
        foodList.remove(index);
        if (actualFood < foodList.size()) {
            actualFood = foodList.size() - 1;
        }
        if (actualFood != -1) {
            GUIShowFood(foodList.get(actualFood));
        } else {
            GUIDeleteFood();
        }
    }
    
    public void remove() {
        foodList.remove(actualFood);
    }
    
    public void saveFood() {
        try {
            fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).write(MainProperties.getInstance().getFoodPath() + "/Food.json", foodList);
        } catch (IOException ex) {
            Logger.getLogger(FoodEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadFood() {
        try {
            
            File file = new File(MainProperties.getInstance().getFoodPath() + "/Food.json");
            if (file.exists()) {
                //System.out.println(file.getAbsolutePath());
                String jsonString = (String) fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).read(file.getAbsolutePath());
                //System.out.println(jsonString);

                Gson gson = new Gson();
                gson.fromJson(jsonString, LinkedList.class);

                List<Food> list = gson.fromJson(jsonString, new TypeToken<List<Food>>() {}.getType());
                list.forEach(x -> {
                    foodList.add(x);
                    ImageIconManager.getInstance().addImage(x.getPath());
                });
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FoodEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmFoodEditor = (FoodEditor)jFrame;
        _init_components_();
    }

    @Override
    public JFrame getFrame() {
        return frmFoodEditor;
    }
    
    
}
