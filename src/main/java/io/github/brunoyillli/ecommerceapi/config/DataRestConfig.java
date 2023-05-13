package io.github.brunoyillli.ecommerceapi.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import io.github.brunoyillli.ecommerceapi.entity.Country;
import io.github.brunoyillli.ecommerceapi.entity.Product;
import io.github.brunoyillli.ecommerceapi.entity.ProductCategory;
import io.github.brunoyillli.ecommerceapi.entity.State;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

	private EntityManager entityManager;

	public DataRestConfig(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		HttpMethod[] theUnsupportedActions = { HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };

		disableHttpMethods(Product.class, config, theUnsupportedActions);
		disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
		disableHttpMethods(Country.class, config, theUnsupportedActions);
		disableHttpMethods(State.class, config, theUnsupportedActions);

		exposeIds(config);
	}

	private void disableHttpMethods(Class<?> theClass, RepositoryRestConfiguration config,
			HttpMethod[] theUnsupportedActions) {
		config.getExposureConfiguration().forDomainType(theClass)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		List<Class<?>> entityClasses = new ArrayList<>();

		for (EntityType<?> tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}

		Class<?>[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}

}
