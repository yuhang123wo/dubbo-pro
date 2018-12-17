package cn.yh.st.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.yh.st.domain.TUser;

/**
 * Created by mavlarn on 2018/1/20.
 */
public interface CustomerRepository extends JpaRepository<TUser, Long> {

	TUser findOneByUsername(String username);
}
