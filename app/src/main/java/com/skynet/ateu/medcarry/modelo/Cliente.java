package com.skynet.ateu.medcarry.modelo;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Cliente extends User {

    private List<String> consultasUIDs = new ArrayList<>();
    private List<String> receitasUIDs = new ArrayList<>();
    private CartaoCredito cartaoCredito;

    public Cliente(){
    }


    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public void setConsultasUIDs(List<String> consultasUIDs) {
        this.consultasUIDs = consultasUIDs;
    }

    public void setReceitasUIDs(List<String> receitasUIDs) {
        this.receitasUIDs = receitasUIDs;
    }

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public List<String> getReceitasUIDs() {
        return receitasUIDs;
    }

    public List<String> getConsultasUIDs() {
        return consultasUIDs;
    }
}
