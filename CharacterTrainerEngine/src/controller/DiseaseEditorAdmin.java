/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import fileioutils.FDBFact;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import model.character.MainCharacter;
import model.disease.Condition;
import model.disease.ConditionFact;
import model.disease.EffectFact;
import model.disease.DiseaseFact;
import model.disease.DiseaseList;
import model.disease.Effect;
import model.disease.interfaces.ICondition;
import model.disease.interfaces.IDisease;
import model.disease.interfaces.IEffect;
import model.utils.ClassReflection;
import model.utils.MainProperties;
import vista.editors.DiseaseEditor;
import vista.listeners.DiseaseBtnAdd;
import vista.listeners.DiseaseBtnAddCondition;
import vista.listeners.DiseaseBtnAddCure;
import vista.listeners.DiseaseBtnAddEffect;
import vista.listeners.DiseaseBtnNext;
import vista.listeners.DiseaseBtnPrevious;
import vista.listeners.DiseaseBtnRemove;
import vista.listeners.DiseaseBtnRemoveCondition;
import vista.listeners.DiseaseBtnRemoveCure;
import vista.listeners.DiseaseBtnRemoveEffect;
import vista.listeners.DiseaseBtnSave;
import vista.listeners.DiseaseLstConditions;
import vista.listeners.DiseaseLstCures;
import vista.listeners.DiseaseLstEffects;
import vista.listeners.DiseaseSldDuration;

/**
 *
 * @author alexander
 */
public class DiseaseEditorAdmin implements IAdmin {
    private DiseaseEditor frmDiseaseEditor;
    
    private DiseaseList diseaseList;
    private DefaultListModel<String> lstCuresModel;
    private DefaultListModel<IEffect> lstEffectsModel;
    private DefaultListModel<ICondition> lstConditionsModel;
    
    private int actualDisease;

    public DiseaseEditorAdmin() {
        _init_();
    }

    public DiseaseEditorAdmin(DiseaseEditor frmDiseaseEditor) {
        this.frmDiseaseEditor = frmDiseaseEditor;
        _init_();
        _init_components_();
    }
    
    private void _init_() {
        diseaseList = new DiseaseList();
        
        loadDisease();
    }
    
    private void _init_components_() {
        frmDiseaseEditor.btnSave.addActionListener(new DiseaseBtnSave(this));
        frmDiseaseEditor.btnPrevious.addActionListener(new DiseaseBtnPrevious(this));
        frmDiseaseEditor.btnNext.addActionListener(new DiseaseBtnNext(this));
        frmDiseaseEditor.btnAdd.addActionListener(new DiseaseBtnAdd(this));
        frmDiseaseEditor.btnDelete.addActionListener(new DiseaseBtnRemove(this));
        frmDiseaseEditor.lstConditions.addListSelectionListener(new DiseaseLstConditions(this));
        frmDiseaseEditor.lstCures.addListSelectionListener(new DiseaseLstCures(this));
        frmDiseaseEditor.lstEffects.addListSelectionListener(new DiseaseLstEffects(this));
        frmDiseaseEditor.sldDuration.addChangeListener(new DiseaseSldDuration(this));
        frmDiseaseEditor.btnAddCondition.addActionListener(new DiseaseBtnAddCondition(this));
        frmDiseaseEditor.btnAddCure.addActionListener(new DiseaseBtnAddCure(this));
        frmDiseaseEditor.btnAddEffect.addActionListener(new DiseaseBtnAddEffect(this));
        frmDiseaseEditor.btnRemoveCondition.addActionListener(new DiseaseBtnRemoveCondition(this));
        frmDiseaseEditor.btnRemoveCure.addActionListener(new DiseaseBtnRemoveCure(this));
        frmDiseaseEditor.btnRemoveEffect.addActionListener(new DiseaseBtnRemoveEffect(this));
        
        lstCuresModel = new DefaultListModel();
        lstEffectsModel = new DefaultListModel();
        lstConditionsModel = new DefaultListModel();
        frmDiseaseEditor.lstCures.setModel(lstCuresModel);
        frmDiseaseEditor.lstEffects.setModel(lstEffectsModel);
        frmDiseaseEditor.lstConditions.setModel(lstConditionsModel);
        
        frmDiseaseEditor.lblDuration.setText(Integer.toString(frmDiseaseEditor.sldDuration.getValue()));
        
        if (diseaseList.size() > 0) {
            GUIShowDisease(diseaseList.getFirst());
            actualDisease = 0;
        }
        
        try {
            String att[] = ClassReflection.cleanAttributes(ClassReflection.getClassAttributes(MainCharacter.class.getSuperclass()));
            for (String at : att) {
                frmDiseaseEditor.cmbConditionAttribute.addItem(at);
            }
            att = ClassReflection.cleanAttributes(ClassReflection.getClassAttributes(MainCharacter.class));
            for (String at : att) {
                frmDiseaseEditor.cmbConditionAttribute.addItem(at);
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DiseaseEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void GUIShowDisease(IDisease disease) {
        GUISetName(disease.getName());
        GUISetDuration(disease.getDuration());
        GUIReplaceConditionsList(disease.getConditions());
        GUIReplaceCuresList(disease.getCures());
        GUIReplaceEffectsList(disease.getEffects());
    }
    
    public void GUIShowCondition(ICondition condition) {
        GUISetConditionAttribute(condition.getAttribute());
        GUISetCondition(condition.getCondition());
        GUISetConditionValue(Integer.toString(condition.getValue()));
    }
    
    public void GUIShowCure(String cure) {
        GUISetCureName(cure);
    }
    
    public void GUIShowEffect(IEffect effect) {
        GUISetEffectActionName(effect.getActionName());
        GUISetEffectAttribute(effect.getAttribute());
        GUISetEffectCondition(effect.getCondition());
        GUISetEffectValue(Integer.toString(effect.getValue()));
    }
    
    //ConditionsList
    
    public void GUIAddConditionsList(Collection<ICondition> conditions) {
        lstConditionsModel.addAll(conditions);
    }
    
    public void GUIReplaceConditionsList(Collection<ICondition> conditions) {
        GUIRemoveConditionsList();
        GUIAddConditionsList(conditions);
    }
    
    public void GUIRemoveConditionsList() {
        lstConditionsModel.removeAllElements();
    }
    
    public void GUIAddConditionList(ICondition condition) {
        lstConditionsModel.addElement(condition);
    }
    
    public void GUIRemoveConditionList() {
        lstConditionsModel.remove(frmDiseaseEditor.lstConditions.getSelectedIndex());
    }
    
    public Enumeration<ICondition> GUIGetConditionsList () {
        return lstConditionsModel.elements();
    }
    
    //CuresList
    
    public void GUIAddCuresList(Collection<String> cures) {
        lstCuresModel.addAll(cures);
    }
    
    public void GUIReplaceCuresList(Collection<String> images) {
        GUIRemoveCuresList();
        GUIAddCuresList(images);
    }
    
    public void GUIRemoveCuresList() {
        lstCuresModel.removeAllElements();
    }
    
    public void GUIAddCureList(String cure) {
        lstCuresModel.addElement(cure);
    }
    
    public void GUIRemoveCureList() {
        lstCuresModel.remove(frmDiseaseEditor.lstCures.getSelectedIndex());
    }
    
    public Enumeration<String> GUIGetCuresList () {
        return lstCuresModel.elements();
    }
    
    //EffectsList
    
    public void GUIAddEffectsList(Collection<IEffect> effects) {
        lstEffectsModel.addAll(effects);
    }
    
    public void GUIReplaceEffectsList(Collection<IEffect> effects) {
        GUIRemoveEffectsList();
        GUIAddEffectsList(effects);
    }
    
    public void GUIRemoveEffectsList() {
        lstEffectsModel.removeAllElements();
    }
    
    public void GUIAddEffectList(IEffect effect) {
        lstEffectsModel.addElement(effect);
    }
    
    public void GUIRemoveEffect() {
        lstEffectsModel.remove(frmDiseaseEditor.lstEffects.getSelectedIndex());
    }
    
    public Enumeration<IEffect> GUIGetEffectsList () {
        return lstEffectsModel.elements();
    }
    
    
    
    public void GUIShowConditionSelected() {
        if (frmDiseaseEditor.lstConditions.getSelectedIndex() != -1) {
            GUIShowCondition(frmDiseaseEditor.lstConditions.getSelectedValue());
        } else {
            GUIShowCondition(ConditionFact.NULL_CONDITION);
        }
    }
    
    public void GUIShowCureSelected() {
        if (frmDiseaseEditor.lstCures.getSelectedIndex() != -1) {
            GUIShowCure(frmDiseaseEditor.lstCures.getSelectedValue());
        } else {
            GUIShowCure("");
        }
    }
    
    public void GUIShowEffectSelected() {
        if (frmDiseaseEditor.lstEffects.getSelectedIndex() != -1) {
            GUIShowEffect(frmDiseaseEditor.lstEffects.getSelectedValue());
        } else {
            GUIShowEffect(EffectFact.NULL_EFFECT);
        }
    }
    
    public void GUIDeleteDisease() {
        GUISetName("");
        GUISetDuration(50);
        GUISetConditionAttribute("");
        GUISetCondition(2);
        GUISetConditionValue("");
        GUISetCureName("");
        GUISetEffectActionName("");
        GUISetEffectAttribute("");
        GUISetEffectCondition(0);
        GUISetEffectRegularity(0);
        GUISetEffectValue("");
        GUIRemoveConditionsList();
        GUIRemoveCuresList();
        GUIRemoveEffectsList();
    }
    
    public String GUIGetName() {
        return frmDiseaseEditor.txtName.getText();
    }
    
    public void GUISetName(String name) {
        frmDiseaseEditor.txtName.setText(name);
    }
    
    public int GUIGetDuration() {
        return frmDiseaseEditor.sldDuration.getValue();
    }
    
    public void GUISetDuration(int duration) {
        frmDiseaseEditor.sldDuration.setValue(duration);
    }
    
    public void GUISetDurationLabel(String duration) {
        frmDiseaseEditor.lblDuration.setText(duration);
    }
    
    public String GUIGetConditionAttribute() {
        return (String)frmDiseaseEditor.cmbConditionAttribute.getSelectedItem();
    }
    
    public void GUISetConditionAttribute(String conditionAttribute) {
        frmDiseaseEditor.cmbConditionAttribute.setSelectedItem(conditionAttribute);
    }
    
    public int GUIGetCondition() {
        return frmDiseaseEditor.cmbCondition.getSelectedIndex() - 2;
    }
    
    public void GUISetCondition(int condition) {
        frmDiseaseEditor.cmbCondition.setSelectedIndex(condition + 2);
    }
    
    public String GUIGetConditionValue() {
        return frmDiseaseEditor.txtConditionValue.getText();
    }
    
    public void GUISetConditionValue(String value) {
        frmDiseaseEditor.txtConditionValue.setText(value);
    }
    
    public int GUIGetConditionsListSelectedNumber() {
        return frmDiseaseEditor.lstConditions.getSelectedIndex();
    }
    
    public int GUIGetCuresListSelectedNumber() {
        return frmDiseaseEditor.lstCures.getSelectedIndex();
    }
    
    public int GUIGetEffectsListSelectedNumber() {
        return frmDiseaseEditor.lstEffects.getSelectedIndex();
    }
    
    public String GUIGetCureName() {
        return frmDiseaseEditor.txtCureName.getText();
    }
    
    public void GUISetCureName(String cureName) {
        frmDiseaseEditor.txtCureName.setText(cureName);
    }
    
    public String GUIGetEffectActionName() {
        return frmDiseaseEditor.txtEffectActionName.getText();
    }
    
    public void GUISetEffectActionName(String effectActionName) {
        frmDiseaseEditor.txtEffectActionName.setText(effectActionName);
    }
    
    public String GUIGetEffectAttribute() {
        return frmDiseaseEditor.txtEffectAttribute.getText();
    }
    
    public void GUISetEffectAttribute(String effectAttribute) {
        frmDiseaseEditor.txtEffectAttribute.setText(effectAttribute);
    }
    
    public int GUIGetEffectCondition() {
        return frmDiseaseEditor.cmbEffectCondition.getSelectedIndex();
    }
    
    public void GUISetEffectCondition(int effectCondition) {
        frmDiseaseEditor.cmbEffectCondition.setSelectedIndex(effectCondition);
    }
    
    public int GUIGetEffectRegularity() {
        return frmDiseaseEditor.cmbEffectRegularity.getSelectedIndex();
    }
    
    public void GUISetEffectRegularity(int effectRegularity) {
        frmDiseaseEditor.cmbEffectRegularity.setSelectedIndex(effectRegularity);
    }
    
    public String GUIGetEffectValue() {
        return frmDiseaseEditor.txtEffectValue.getText();
    }
    
    public void GUISetEffectValue(String value) {
        frmDiseaseEditor.txtEffectValue.setText(value);
    }
    
    public void addDisease(IDisease disease) {
        diseaseList.add(disease);
    }
    
    public void nextDisease() {
        actualDisease++;
        if (actualDisease < diseaseList.size()) {
            GUIShowDisease(diseaseList.get(actualDisease));
        } else {
            actualDisease = diseaseList.size() - 1;
            GUIShowDisease(diseaseList.get(actualDisease));
        }
    }
    
    public void previousDisease() {
        actualDisease--;
        if (actualDisease >= 0) {
            GUIShowDisease(diseaseList.get(actualDisease));
        } else {
            actualDisease = 0;
            GUIShowDisease(diseaseList.get(actualDisease));
        }
    }
    
    public void addFirst(IDisease disease) {
        diseaseList.addFirst(disease);
    }
    
    public void addLast(IDisease disease) {
        diseaseList.addLast(disease);
    }
    
    public void addCure(String cure) {
        GUIAddCureList(cure);
    }
    
    public void addEffect(IEffect effect) {
        GUIAddEffectList(effect);
    }
    
    public void addCondition(ICondition condition) {
        GUIAddConditionList(condition);
    }
    
    public void add(int index, IDisease disease) {
        diseaseList.add(index, disease);
    }
    
    public void add(IDisease disease) {
        if (diseaseList.size() > 0) {
            diseaseList.add(actualDisease, disease);
        } else {
            addFirst(disease);
        }
    }
    
    public void remove(int index) {
        diseaseList.remove(index);
        if (actualDisease < diseaseList.size()) {
            actualDisease = diseaseList.size() - 1;
        }
        if (actualDisease != -1) {
            GUIShowDisease(diseaseList.get(actualDisease));
        } else {
            GUIDeleteDisease();
        }
    }
    
    public void remove() {
        diseaseList.remove(actualDisease);
    }
    
    public void removeCondition() {
        int conditionSelected = GUIGetConditionsListSelectedNumber();
        if (conditionSelected != -1) {
            lstConditionsModel.remove(conditionSelected);
        }
    }
    
    public void removeCure() {
        int cureSelected = GUIGetCuresListSelectedNumber();
        if (cureSelected != -1) {
            lstCuresModel.remove(cureSelected);
        }
    }
    
    public void removeEffect() {
        int effectSelected = GUIGetEffectsListSelectedNumber();
        if (effectSelected != -1) {
            lstEffectsModel.remove(effectSelected);
        }
    }
    
    public void saveDisease() {
        try {
            //System.out.println(MainProperties.getInstance().getDiseasePath() + MainProperties.getInstance().getPathSeparator() + "Disease.json");
            fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).write(MainProperties.getInstance().getDiseasePath() + MainProperties.getInstance().getPathSeparator() + "Disease.json", diseaseList);
        } catch (IOException ex) {
            Logger.getLogger(DiseaseEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDisease() {
        try {
            
            File file = new File(MainProperties.getInstance().getDiseasePath() + MainProperties.getInstance().getPathSeparator() + "Disease.json");
            if (file.exists()) {
                String jsonString = (String) fileioutils.FDBFact.getIntance().create(FDBFact.DB_TYPE.DBJson).read(file.getAbsolutePath());

                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
                
                LinkedList diseases = gson.fromJson(jsonString, new TypeToken<LinkedList<JsonObject>>() {}.getType());
                
                diseases.forEach(disease -> {
                    String name = ((JsonObject)disease).get("name").getAsString();
                    int duration = ((JsonObject)disease).get("duration").getAsInt();
                    
                    IDisease newDisease = DiseaseFact.create(name, duration);
                    
                    // Conditions
                    
                    JsonElement jeConditions = ((JsonObject)disease).get("conditions");
                    
                    LinkedList llConditions = gson.fromJson(jeConditions, new TypeToken<LinkedList<Condition>>() {}.getType());
                    
                    llConditions.forEach(condition -> newDisease.addCondition((ICondition)condition));
                    
                    // Cures
                    
                    JsonElement jeCures = ((JsonObject)disease).get("cures");
                    
                    LinkedList llCures = gson.fromJson(jeCures, new TypeToken<LinkedList<String>>() {}.getType());
                    
                    llCures.forEach(cure -> newDisease.addCure((String)cure));
                    
                    // Effects
                    
                    JsonElement jeEffects = ((JsonObject)disease).get("effects");
                    
                    LinkedList llEffects = gson.fromJson(jeEffects, new TypeToken<LinkedList<Effect>>() {}.getType());
                    
                    llEffects.forEach(effect -> newDisease.addEffect((IEffect)effect));
                    
                    diseaseList.add(newDisease);
                });
            }
            
        } catch (IOException ex) {
            Logger.getLogger(DiseaseEditorAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setFrame(JFrame jFrame) {
        this.frmDiseaseEditor = (DiseaseEditor)jFrame;
        _init_components_();
    }

    @Override
    public JFrame getFrame() {
        return frmDiseaseEditor;
    }
    
    
}
