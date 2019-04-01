package Domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

import Exception.DuplicateFileException;
import Exception.InvalidFileException;
import javafx.collections.ObservableList;

public class FileTbl implements IFileTbl {
	public HashMap<Integer, BufferedReader> files;
	
	public FileTbl() {
		files = new HashMap<>();
	}
	
	public FileTbl(FileTbl f) {
		files = new HashMap<>(f.files);
	}
	
	@Override
	public BufferedReader getFile(int i) throws InvalidFileException {
		BufferedReader file;
		if ((file = files.get(i)) == null)
			throw new InvalidFileException("No file with name *" + i + "*",0);
		return file;
	}

	@Override
	public void addFile(int i, BufferedReader br) throws DuplicateFileException {
		try {
			this.getFile(i);
			throw new DuplicateFileException("There is already a file with name *" + i + "",0);
		} catch (InvalidFileException e) {
			files.put(i, br);
		}
	}

	@Override
	public void removeFile(int i) throws InvalidFileException {
		this.getFile(i);
		files.remove(i);
	}

	@Override
	public int count() {
		return files.size();
	}

	@Override
	public boolean contains(int key) {
		try {
			this.getFile(key);
			return true;
		} catch (InvalidFileException e) {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String toPrint = "\r\nFileTable: \r\n";
		boolean empty = true;
		for (Integer i : files.keySet()) {
			boolean text = files.get(i) == null;
			toPrint += "\t" + i + " = " + text + "\n";
			empty = false;
		}
		if (empty)
			toPrint += "\tEmpty\n";
		return toPrint;
	}
	
	@Override
	public ObservableList<FileTblItem> toObservableList(ObservableList<FileTblItem> list){
		list.clear();
		for (Integer key : files.keySet()) {
			try {
				list.add(new FileTblItem(key, files.get(key) == null ? "NULL" : Boolean.toString(files.get(key).ready())));
			} catch (IOException e) {
				list.add(new FileTblItem(key, files.get(key) == null ? "NULL" : "File does not exist" ));
				e.printStackTrace();
			}
		}
		return list;
	}

}
