/**
 * Created by quzhou on 10/19/17.
 */
public class ExcelTitle {
    public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while (n > 0) {
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }
}
