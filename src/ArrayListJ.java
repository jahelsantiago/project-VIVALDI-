import java.io.Serializable;
import java.util.Arrays;





public class ArrayListJ implements Serializable{
	private Object[] data;
	private int lenght = 0;
	private int max_lenght = 10;

	public ArrayListJ() {
		data = new Object[this.max_lenght];
	}

	public Object get(int index) {
		if (index < lenght) {
			return data[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}


	public void add(Object obj) {
		if (data.length - lenght <= data.length / 2) {
			this.reSizeArray();
		}

		data[lenght++] = obj;
	}


	public Object remove(int index) {
		if (index < lenght) {
			Object obj = data[index];
			int temp = index;
			data[index] = null;

			while (temp < lenght) {
				data[temp] = data[temp + 1];
				data[temp + 1] = null;
				temp++;
			}

			lenght--;
			return obj;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}


	public void reSizeArray() {
		data = Arrays.copyOf(data, data.length * 2);
	}

	public int size() {
		return lenght;
	}


}