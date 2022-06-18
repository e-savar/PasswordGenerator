public class PasswordGeneratorSettings {
    
    private boolean allowsCaps;
    private boolean allowsNums;
    private boolean allowsSpecialChars;

    public PasswordGeneratorSettings() {
        allowsCaps = false;
        allowsNums = false;
        allowsSpecialChars = false;
    }

    public PasswordGeneratorSettings(boolean allowsCaps, boolean allowsNums, boolean allowsSpecialChars)
    {
        this.allowsCaps = allowsCaps;
        this.allowsNums = allowsNums;
        this.allowsSpecialChars = allowsSpecialChars;
    }

    public void changeCaps(boolean value)
    {
        allowsCaps = value;
    }
    public void changeNums(boolean value)
    {
        allowsNums = value;
    }
    public void changeSpecialChars(boolean value)
    {
        allowsSpecialChars = value;
    }

    public boolean getCaps()
    {
        return allowsCaps;
    }
    public boolean getNums()
    {
        return allowsNums;
    }
    public boolean getSpecialChars()
    {
        return allowsSpecialChars;
    }
}
