package org.osforce.connect.service.blog;

import java.util.List;

import org.osforce.connect.entity.blog.BlogCategory;
import org.osforce.connect.entity.system.Project;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 12, 2011 - 4:19:16 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public interface BlogCategoryService {

	BlogCategory getBlogCategory(Long categoryId);
	
	BlogCategory getBlogCategory(Long projectId, String categoryLabel);
	
	void createBlogCategory(BlogCategory category);
	
	void updateBlogCategory(BlogCategory category);
	
	void deleteBlogCategory(Long categoryId);

	List<BlogCategory> getBlogCategoryList(Project project);
}
