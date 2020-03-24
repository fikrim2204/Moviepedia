package rpl1pnp.fikri.moviepedia.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public abstract class BaseModel<MovieData> {

	@SerializedName("page")
	private int page;

	@SerializedName("results")
	private List<MovieData> results;

	public BaseModel(){

	}

	public void setPage(int page){
		this.page = page;
	}

	public int getPage(){
		return page;
	}

	public void setResults(List<MovieData> results){
		this.results = results;
	}

	public List<MovieData> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"BaseModel{" +
			"page = '" + page + '\'' +
			",results = '" + results + '\'' +
			"}";
		}

}