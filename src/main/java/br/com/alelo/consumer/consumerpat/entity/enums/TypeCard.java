package br.com.alelo.consumer.consumerpat.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeCard {

    FOOD(0, "FOOD"),
    FUEL(1, "FUEL"),
    DRUG_STORE(2, "DRUG_STORE");

    private Integer code;
    private String description;

    public static TypeCard toEnum(Integer cod) {
        if(cod == null) {
            return null;
        }

        for(TypeCard x : TypeCard.values()) {
            if(x.getCode().equals(cod)) {
                return x;
            }
        }

        throw new IllegalArgumentException("Código inválido");
    }
}
