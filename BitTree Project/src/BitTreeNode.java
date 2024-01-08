public class BitTreeNode implements Comparable<Object> {
    String chr;
    int freq;
    BitTreeNode left;
    BitTreeNode right;
    String code;

    public BitTreeNode(int freq, BitTreeNode left, BitTreeNode right)
    {
        this.chr = null;
        this.freq = freq;
        this.right = right;
        this.left = left;
    }

    public BitTreeNode(int freq, String chr) {
        this.chr = chr;
        this.freq = freq;
        right = null;
        left = null;
    }

    public BitTreeNode(BitTreeNode left, BitTreeNode right) {
     this.left = left;
     this.right = right;
    }

    public void setCode(int code)
    {
        this.code = "" + code;
    }

    public String getCode()
    {

        return code;
    }

    public String getChr() {
         return chr;
    }

    public int getFreq()
    {
        return freq;
    }


    public String toString()
    {
        return "Character: " + chr + "      " + "Frequency: " + freq +  "      ";
    }

    @Override
    public int compareTo(Object o) {

       return 0;
    }




    public int compareTo(BitTreeNode o) {
        if(this.freq == o.freq)
        {
            if(this.chr != null && o.chr != null)
            {
                return this.chr.compareTo(o.chr);
            }else if(this.chr == null) {
                return 1;
            }else if(o.chr == null)
            {
                return -1;
            }else{
                return -1;
            }
        }else {
            return this.freq - o.freq;
        }
    }
 }
