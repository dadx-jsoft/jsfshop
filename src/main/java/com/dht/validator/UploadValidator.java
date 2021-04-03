package com.dht.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@FacesValidator(value = "UploadValidator")
public class UploadValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		Part p = (Part) value;
		
		// Ảnh phải là png hoặc jpg
		if(!p.getContentType().equals("image/png") &&
		   !p.getContentType().equals("image/jpg")) {
			System.out.println(p.getContentType());
			FacesMessage msg = new FacesMessage("Need png/jpg");
			throw new ValidatorException(msg);
		}
		
		// Dung lượng phải nhỏ hơn hoặc bằng 2MB
		if(p.getSize()>2097152) {
			FacesMessage msg = new FacesMessage("Size <= 2MB");
			throw new ValidatorException(msg);
		}
		
	}

}
