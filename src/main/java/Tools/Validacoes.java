package Tools;

public class Validacoes {
    public static int contarDigitos(int numero) {
        return String.valueOf(numero).length();
    }
    
    public static boolean validarCPF(String cpf) {
        if (cpf == null || cpf.isEmpty()) return false;

        // Remove caracteres não numéricos
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) return false;

        if (!cpf.matches("\\d{11}")) return false;

        char[] cpfDigits = cpf.toCharArray();

        // Verificação do primeiro dígito verificador
        int digitCheckerOne = 0;
        for (int i = 0, f = 10; i < 9; i++, f--) {
            digitCheckerOne += Character.getNumericValue(cpfDigits[i]) * f;
        }
        digitCheckerOne = (digitCheckerOne * 10) % 11;
        if (digitCheckerOne == 10) digitCheckerOne = 0;

        // Verificação do segundo dígito verificador
        int digitCheckerTwo = 0;
        for (int i = 0, f = 11; i < 10; i++, f--) {
            digitCheckerTwo += Character.getNumericValue(cpfDigits[i]) * f;
        }
        digitCheckerTwo = (digitCheckerTwo * 10) % 11;
        if (digitCheckerTwo == 10) digitCheckerTwo = 0;

        // Comparação dos dígitos verificadores
        return digitCheckerOne == Character.getNumericValue(cpfDigits[9]) &&
               digitCheckerTwo == Character.getNumericValue(cpfDigits[10]);
    }
}
