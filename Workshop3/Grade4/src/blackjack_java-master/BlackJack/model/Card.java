package BlackJack.model;

public class Card
{
    
    public enum Color
    {
        Hearts,
        Spades,
        Diamonds,
        Clubs,
        Count,
        Hidden
    }
    
    public enum Value
    {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Knight,
        Queen,
        King,
        Ace,
        Count,
        Hidden
    }
    
    private Color color;
    private Value value;
    private boolean isHidden;
    
    public Card(Color a_color, Value a_value)
    {
        value = a_value;
        color = a_color;
        isHidden = true;
    }
    
    public Color GetColor()
    {
        if (isHidden)
        {
            return Color.Hidden;
        }
        return color;
    }
    
    public Value GetValue()
    {
        if (isHidden)
        {
            return Value.Hidden;
        }
        return value;
    }
    
    public void Show(boolean a_show)
    {
        isHidden = !a_show;
    }
}