package com.noterror.app.api.vegetarian;

import com.noterror.app.api.entity.Vegetarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VegetarianRepository extends JpaRepository<Vegetarian, String> {
}
