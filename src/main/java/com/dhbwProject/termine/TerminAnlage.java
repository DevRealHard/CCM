package com.dhbwProject.termine;

import java.util.Date;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.Reindeer;

public class TerminAnlage extends CustomComponent {
	private static final long serialVersionUID = 1L;
	
	private TerminFields fields;
	private VerticalLayout vlLayout;
	private Button btnCreate;	
	
	public TerminAnlage(){
		this.initFields();
		this.initVlLayout();
	}
	
	public TerminAnlage(Date date){
		this();
		this.setDate(date);
	}
	
	private void initFields(){
		this.fields = new TerminFields();
		fields.initFieldTitel();
		fields.initDfDate();
		fields.initFieldUnternehmen();
		fields.initFieldAnsprechpartner();
		fields.initFieldParticipants();
		this.initBtnCreate();
	}
	
	private void initBtnCreate(){
		this.btnCreate = new Button("Termin erstellen");
		this.btnCreate.setIcon(FontAwesome.PLUS);
		this.btnCreate.setWidth("300px");
		this.btnCreate.addClickListener(listener ->{
			this.createTermin();	
		});
		
		this.fields.addComponent(this.btnCreate);
	}
	
	private void initVlLayout(){
		this.vlLayout = new VerticalLayout(this.fields);
		this.vlLayout.setSizeFull();
		this.vlLayout.setComponentAlignment(this.fields, Alignment.TOP_LEFT);
		this.vlLayout.setStyleName(Reindeer.LAYOUT_BLUE);
		this.setCompositionRoot(this.vlLayout);
	}
	
	protected void setDate(Date d){
		this.fields.setDate(d);
	}
	
	protected Button getBtnCreate(){
		return this.btnCreate;
	}
	
	private void createTermin(){
		/*	Hier muss eine Abfrage erfolgen ob bei diesem Unternehmen
		 *	vor kurzem oder in naher Zukunft Termine geplant waren
		 *	oder bereits geplant sind*/
		boolean b = false;
		if(b){
			//erstelle termin 
		}else
			this.createWarning();
	}
	
	private void createWarning(){
		Window warning = new Window();
		VerticalLayout vlFields = new VerticalLayout();
		Button btnYes = new Button("Ja");
		Button btnNo = new Button("Nein");
		
		btnYes.setWidth("200px");
		btnYes.setIcon(FontAwesome.CHECK);
		btnYes.addClickListener(listener ->{
			//erstelle anlage
			Notification.show("Termin wurde erstellt");
			warning.close();
		});
		
		btnNo.setWidth("200px");
		btnNo.setIcon(FontAwesome.CLOSE);
		btnNo.addClickListener(listener ->{
			Notification.show("Termin wurde nicht erstellt");
			warning.close();
		});
		
		vlFields.setWidth("100%");
		vlFields.setMargin(new MarginInfo(true, false, true, false));
		vlFields.addComponent(btnYes);
		vlFields.addComponent(btnNo);
		vlFields.setComponentAlignment(btnYes, Alignment.MIDDLE_CENTER);
		vlFields.setComponentAlignment(btnNo, Alignment.MIDDLE_CENTER);
		
		
		warning.setCaptionAsHtml(true);
		warning.setCaption("<center><h1>Achtung!</h1>"
				+ "<p>Bei diesem Unternehmen sind zeitnahe Termine bereits in planung</p>"
				+ "<p>Möchten Sie diesen Termin dennoch erstellen?</p><center>");
		warning.setSizeFull();
		warning.setModal(true);
		warning.setClosable(false);
		warning.setContent(vlFields);
		
		this.getUI().addWindow(warning);
	}
	
}
