import java.util.HashMap;
import java.util.Map;

public class SiparisAnaliz {
    public static void main(String[] args) {
        // Verilen tablodaki verileri temsil eden dizi
        String[][] veriler = {
                {"1000", "2000", "12", "100.51"},
                {"1000", "2001", "31", "200"},
                {"1000", "2002", "22", "150.86"},
                {"1000", "2003", "41", "250"},
                {"1000", "2004", "55", "244"},
                {"1001", "2001", "88", "44.531"},
                {"1001", "2002", "121", "88.11"},
                {"1001", "2004", "74", "211"},
                {"1001", "2002", "14", "88.11"},
                {"1002", "2003", "2", "12.1"},
                {"1002", "2004", "3", "22.3"},
                {"1002", "2003", "8", "12.1"},
                {"1002", "2002", "16", "94"},
                {"1002", "2005", "9", "44.1"},
                {"1002", "2006", "19", "90"}
        };

        // a. Üç siparişteki malların toplam tutarı
        double toplamTutar = 0;
        for (int i = 0; i < veriler.length; i++) {
            double fiyat = Double.parseDouble(veriler[i][3]);
            toplamTutar += Integer.parseInt(veriler[i][2]) * fiyat;
        }
        System.err.println("a. Üç siparişteki malların toplam tutarı: " + toplamTutar + " TL");

        // b. Üç siparişteki bütün malların ortalama fiyatı
        double toplamFiyat = 0;
        int toplamAdet = 0;
        for (int i = 0; i < veriler.length; i++) {
            toplamFiyat += Double.parseDouble(veriler[i][3]);
            toplamAdet += Integer.parseInt(veriler[i][2]);
        }
        double ortalamaFiyat = toplamFiyat / toplamAdet;
        System.err.println("b. Üç siparişteki bütün malların ortalama fiyatı: " + ortalamaFiyat + " TL");

        // c. Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatları
        Map<String, Double> malOrtalamaFiyatlar = new HashMap<>();
        for (int i = 0; i < veriler.length; i++) {
            String malNumarasi = veriler[i][1];
            double malFiyati = Double.parseDouble(veriler[i][3]);
            int adet = Integer.parseInt(veriler[i][2]);
            double toplamFiyatMali = malOrtalamaFiyatlar.getOrDefault(malNumarasi, 0.0) + (malFiyati * adet);
            malOrtalamaFiyatlar.put(malNumarasi, toplamFiyatMali);
        }

        System.err.println("c. Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatları:");
        for (Map.Entry<String, Double> entry : malOrtalamaFiyatlar.entrySet()) {
            String malNumarasi = entry.getKey();
            double ortalamaFiyatMali = entry.getValue() / toplamAdet; // Toplam adete bölerek ortalama fiyatı buluyoruz.
            System.out.println("Mal Numarası: " + malNumarasi + ", Ortalama Fiyat: " + ortalamaFiyatMali + " TL");
        }

        // d. Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğu
        Map<String, Map<String, Integer>> malSiparisAdetleri = new HashMap<>();
        for (int i = 0; i < veriler.length; i++) {
            String siparisNumarasi = veriler[i][0];
            String malNumarasi = veriler[i][1];
            int adet = Integer.parseInt(veriler[i][2]);
            Map<String, Integer> siparisAdetleri = malSiparisAdetleri.getOrDefault(malNumarasi, new HashMap<>());
            siparisAdetleri.put(siparisNumarasi, siparisAdetleri.getOrDefault(siparisNumarasi, 0) + adet);
            malSiparisAdetleri.put(malNumarasi, siparisAdetleri);
        }

        System.err.println("d. Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğu:");
        for (Map.Entry<String, Map<String, Integer>> entry : malSiparisAdetleri.entrySet()) {
            String malNumarasi = entry.getKey();
            Map<String, Integer> siparisAdetleri = entry.getValue();
            System.out.println("Mal Numarası: " + malNumarasi + ", Sipariş Adetleri: " + siparisAdetleri);
        }
    }
}
