package com.skynet.ateu.medcarry.util;

public class Validacoes {

    /** Método para validaçao de cnpj
     * @param  cnpj - Cnpj de pessoa
     *   @return boolean - Validade do cnpj*/
    public static boolean validaCnpj(String cnpj) {
        if(cnpj.length() > 14 || cnpj.length() < 14) return false;
        if(cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222") ||
                cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888") ||
                cnpj.equals("99999999999999")) return false;
        int peso = 2;
        int soma = 0;
        int dig1,dig2;
        for(int i = 11; i>=0; i--) {
            int n = (int)(cnpj.charAt(i)-48);
            soma += (n * peso);
            peso++;
            if(peso == 10) peso = 2;
        }
        int aux = soma % 11;
        if(aux == 0 || aux == 1) dig1 = 0;
        else dig1 = 11-aux;

        soma = 0;
        peso = 2;
        for(int i = 12; i>=0; i--) {
            int n = (int)(cnpj.charAt(i)-48);
            soma += (n* peso);
            peso++;
            if(peso == 10) peso = 2;
        }

        aux = soma % 11;
        if(aux == 0 || aux == 1) dig2 = 0;
        else dig2 = 11-aux;
        if((dig1 == ( (int) (cnpj.charAt(12)- 48) ) && dig2 == ( (int) (cnpj.charAt(13)- 48) ))) return true;
        else return false;
    }

    /** Método para validaçao de cpf
     * @param  cpf - Cpf de pessoa
     *   @return boolean - Validade do cpf*/
    public static boolean validaCpf(String cpf){
        if(cpf.length() > 11 || cpf.length() < 11) return false;
        if(cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") ||
                cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") ||
                cpf.equals("99999999999")) return false;

        int soma = 0;
        int peso = 10;
        int dig1,dig2;
        for(int i = 0; i<9; i++) {
            int n = (int)(cpf.charAt(i)-48);
            soma += (n * peso);
            peso--;
        }
        int aux = 11 - (soma % 11);
        if(aux == 0 || aux == 1) dig1 = 0;
        else dig1 = aux;

        soma = 0;
        peso = 11;
        for(int i = 0; i < 10; i++) {
            int n = (int)(cpf.charAt(i)-48);
            soma += (n * peso);
            peso--;
        }
        aux = 11 - (soma % 11);
        if(aux == 10 || aux == 11) dig2 = 0;
        else dig2 = aux;

        if( (dig1 == ( (int) (cpf.charAt(9)-48) ) ) && dig2 == ( (int) (cpf.charAt(10)-48) ) ) return true;
        else {
            return false;
        }

    }
    public static boolean validaTelefone(String telefone){
        char[] telefoneChars = telefone.toCharArray();
        if(telefone.length() > 9 && telefone.length() < 8)return false;
        if(telefone.length() == 9 && telefoneChars[0] != '9')return false;


        return true;
    }
}
