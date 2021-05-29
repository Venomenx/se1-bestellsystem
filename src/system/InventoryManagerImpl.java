package system;

import java.util.Optional;

import datamodel.Article;
import system.Components.InventoryManager;

final class InventoryManagerImpl implements Components.InventoryManager{

	public InventoryManagerImpl() {
	}

	@Override
	public boolean containsArticle(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Article> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Article> get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InventoryManager add(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryManager remove(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
