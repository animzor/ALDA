//Johnny Huhtala
//johu7086

/**
 * Denna klass representerar noderna i ett bin�rt s�ktr�d utan dubletter.
 * 
 * Detta �r den enda av de tre klasserna ni ska g�ra n�gra �ndringar i. (Om ni
 * inte vill l�gga till fler testfall.) De �ndringar som �r till�tna �r dock
 * begr�nsade av f�ljande regler:
 * <ul>
 * <li>Ni f�r INTE l�gga till n�gra fler instansvariabler.
 * <li>Ni f�r INTE l�gga till n�gra statiska variabler.
 * <li>Ni f�r INTE anv�nda n�gra loopar n�gonstans.
 * <li>Ni F�R l�gga till fler metoder, dessa ska d� vara privata.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {

	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	/**
	 * L�gger till en nod i det bin�ra s�ktr�det. Om noden redan existerar s�
	 * l�mnas tr�det of�r�ndrat.
	 * 
	 * @param data
	 *            datat f�r noden som ska l�ggas till.
	 * @return true om en ny nod lades till tr�det.
	 */

	//add-metoden delvis från:
	//http://www.algolist.net/Data_structures/Binary_search_tree/Insertion
//	public boolean add(T data) {
//		if (this.data == data) {
//			return false;
//		}
//
//		if (data.compareTo(this.data) < 0) {
//			if (left == null) {
//				left = new BinarySearchTreeNode<T>(data);
//				return true;
//			} else {
//				return left.add(data);
//			}
//		} else {
//			if (right == null) {
//				right = new BinarySearchTreeNode<T>(data);
//				return true;
//			} else {
//				return right.add(data);
//			}
//		}
//	}
	
	public boolean add(T data) {
		  if (data == null){
		   return false;
		  }

		  int compareInt = data.compareTo(this.data);

		  if (compareInt < 0){
		   if (this.left == null){
		    this.left = new BinarySearchTreeNode<T>(data);
		    return true;
		   } else {
		    this.left.add(data);
		   }
		  } else if (compareInt > 0){
		   if (this.right == null){
		    this.right = new BinarySearchTreeNode<T>(data);
		    return true;
		   } else {
		    this.right.add(data);
		   }
		  }
		  return false;
		 }

	/**
	 * Privat hj�lpmetod som �r till nytta vid borttag. Ni beh�ver inte
	 * skriva/utnyttja denna metod om ni inte vill.
	 * 
	 * @return det minsta elementet i det (sub)tr�d som noden utg�r root i.
	 */
	private T findMin() {
		if (left == null) {
			return this.data;
		} else {
			return left.findMin();
		}

	}

	/**
	 * Tar bort ett element ur tr�det. Om elementet inte existerar s l�mnas
	 * tr�det of�r�ndrat.
	 * 
	 * @param data
	 *            elementet som ska tas bort ur tr�det.
	 * @return en referens till nodens subtr�d efter borttaget.
	 */
	
	//remove-metoden delvis från kursboken
	public BinarySearchTreeNode<T> remove(T data) {

		BinarySearchTreeNode<T> node = this;

		if (data.compareTo(this.data) < 0) {
			if(this.left != null){
			this.left = this.left.remove(data);
			}
		} else if(data.compareTo(this.data) > 0) {
			if(this.right != null){
			this.right = this.right.remove(data);
			}
		} else if(this.left != null && this.right != null) {
			this.data = this.right.findMin();
			this.right = this.right.remove(this.data);
		} else
			node = (this.left != null) ? this.left : this.right;
		return node;
	}
	

	/**
	 * Kontrollerar om ett givet element finns i det (sub)tr�d som noden utg�r
	 * root i.
	 * 
	 * @param data
	 *            det s�kta elementet.
	 * @return true om det s�kta elementet finns i det (sub)tr�d som noden utg�r
	 *         root i.
	 */
	public boolean contains(T data) {

		if (data.compareTo(this.data) > 0) {
			if (this.right == null) {
				return false;
			}
			return this.right.contains(data);
		} else if (data.compareTo(this.data) < 0) {
			if (this.left == null) {
				return false;
			}
			return this.left.contains(data);
		} else if (data.compareTo(this.data) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Storleken p� det (sub)tr�d som noden utg�r root i.
	 * 
	 * @return det totala antalet noder i det (sub)tr�d som noden utg�r root i.
	 */
	public int size() {

		if (this.left == null && this.right == null) {
			return 1;
		} else if (this.left == null && this.right != null) {
			return 1 + this.right.size();
		} else if (this.left != null && this.right == null) {
			return 1 + this.left.size();
		} else if (this.left != null && this.right != null) {
			return 1 + this.left.size() + this.right.size();
		}
		return 0;
	}

	/**
	 * Det h�gsta djupet i det (sub)tr�d som noden utg�r root i.
	 * 
	 * @return djupet.
	 */
	public int depth() {
		int leftDepth = 0;
		int rightDepth = 0;

		if (this.left != null) {
			leftDepth = this.left.depth() + 1;
		}
		if (this.right != null) {
			rightDepth = this.right.depth() + 1;
		}

		if(leftDepth > rightDepth){
			return leftDepth;
		}
		else
			return rightDepth;
	}

	/**
	 * Returnerar en str�ngrepresentation f�r det (sub)tr�d som noden utg�r root
	 * i. Denna representation best�r av elementens dataobjekt i sorterad
	 * ordning med ", " mellan elementen.
	 * 
	 * @return str�ngrepresentationen f�r det (sub)tr�d som noden utg�r root i.
	 */
	public String toString() {
		String str = "";
		if (left != null) {
			str = str + left.toString() + ", ";
		}
		str = str + this.data;
		if (right != null) {
			str = str + ", " + right.toString();
		}
		return str;

	}
}