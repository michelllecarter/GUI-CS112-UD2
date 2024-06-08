//package replit;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/* UML CLASS DIAGRAM:
-----------------------------------------
NewItem
-----------------------------------------
- cardName : String
- imageName : String
- cardNum : int
-----------------------------------------
+ NewItem(itemName : String, imageName : String, itemNum : int)
+ NewItem(original : NewItem)
+ setAll(itemName : String, imageName : String, itemNum : int)
+ getItemName() : String
+ setItemName(itemName : String) : void
+ getImageName() : String
+ getImage() : Image
+ setImageName(imageName : String) : void
+ getItemNum() : int
+ setItemNum(itemNum : int) : void
+ toString() : String
-----------------------------------------
*/

public class NewItem
{
        // INSTANCE VARIABLES
        private String itemName = "";
        private String imageName = "";
        private int itemNum = 0;

        //Constructor
        public NewItem(String itemName, String imageName, int itemNum)
        {
                this.setAll(itemName, imageName, itemNum);
        }

        //Copy constructor with error checking for null values
        public NewItem(NewItem original)
        {
                if (original != null)
                {
                        this.setAll(original.itemName, original.imageName, original.itemNum);
                }
                else
                {
                        System.out.println("ERROR: trying to copy NULL object. Exiting program...");
                        System.exit(1);
                }
        }

        //default constructor creates false New Item
        public NewItem() {
                this("New Item Logo", "Oatly.jpg", 0);
        }

        // get/set methods
        public void setAll(String itemName, String imageName, int itemNum)
        {
                this.setItemName(itemName);
                this.setImageName(imageName);
                this.setItemNum(itemNum);
        }

        public String getItemName()
        {
                return this.itemName;
        }

        public void setItemName(String itemName)
        {
                this.itemName = itemName;
        }

        public String getImageName()
        {
                return this.imageName;
        }

        /**
         * Gets Image object for this item by reading it from the appropriate location in a replit project
         *
         * @return returns Image object that can be used to display it in an ImageView in GUI
         **/
        public Image getImage() {
                FileInputStream input = null;
                try {
                        input = new FileInputStream("./resources/" + this.imageName);
                } catch (FileNotFoundException e) {
                        //e.printStackTrace();
                        System.err.println("ERROR: could not open Loteria card image file.");
                        System.exit(0);
                }
                return new Image(input);
        }

        public void setImageName(String imageName)
        {
                this.imageName = imageName;
        }

        public int getItemNum()
        {
                return itemNum;
        }

        public void setItemNum(int itemNum)
        {
                this.itemNum = itemNum;
        }

        //toString method
        @Override
        public String toString()
        {
                return "NewItem #" + this.itemNum + ": " + this.itemName + "(" + this.imageName + ")";
        }

        //Equals method
        @Override
        public boolean equals(Object o)
        {
                if(o == null || !(o instanceof NewItem))
                {
                        return false;
                }
                NewItem other = (NewItem) o;

                return this.itemName.equals(other.itemName) && this.itemNum == other.itemNum
                                && this.imageName.equals(other.imageName);
        }
}
