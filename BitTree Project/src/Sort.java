public class Sort {

    /**public Sort(BitTreeNode[] btn)
    {
        int[] frequencies = new int[btn.length];
        bubble(btn);
      //  for()
    }**/
    public static BitTreeNode[] bubble(BitTreeNode[] btn)
    {
        BitTreeNode temp = null;

        boolean switched = true;

        int numSwitched = 0;

        while(switched)
        {
            for(int i = 0; i < btn.length - 1; i++)
            {
                if(btn[i + 1].compareTo(btn[i]) < 0)
                {
                    temp = btn[i + 1];
                    btn[i + 1] = btn[i];
                    btn[i] = temp;
                    numSwitched++;
                    switched = true;
                }
            }

            if(numSwitched > 0)
            {
                switched = true;
            }else{
                switched = false;
            }
            numSwitched = 0;
        }

        /**for(int i = 0; i < btn.length; i++)
        {
            System.out.println(btn[i]);
        }**/
        return btn;
    }

    /**private boolean isLess(BitTreeNode[] btn, BitTreeNode a, BitTreeNode b)
    {
        if(a != null && b != null && a.compareTo(b) != 0)
        {
            return a.compareTo(b) <
        }
    }**/
}
