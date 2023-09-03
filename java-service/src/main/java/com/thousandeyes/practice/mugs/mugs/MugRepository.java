package com.thousandeyes.practice.mugs.mugs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MugRepository extends JpaRepository<Mug, Integer> {
    Mug findMugById(int id);
}
