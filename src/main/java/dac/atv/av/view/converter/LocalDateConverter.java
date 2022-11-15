package dac.atv.av.view.converter;


import jakarta.annotation.PostConstruct;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter<LocalDate> {

    private final DateTimeFormatter formatter;

    public LocalDateConverter(){
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public LocalDate getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return LocalDate.parse(s, this.formatter);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, LocalDate localDate) {
        return formatter.format(localDate);
    }
}
