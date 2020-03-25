package com.haha.xixi.service.impl;

import com.haha.xixi.model.UserBaseExtModel;
import com.haha.xixi.model.UserBaseModel;
import com.haha.xixi.repository.IuserBaseExtManageRepo;
import com.haha.xixi.repository.IuserBaseManageRepo;
import com.haha.xixi.service.IuserBaseExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UserBaseExtServiceImpl implements IuserBaseExtService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IuserBaseManageRepo baseRepo;

	@Autowired
	private IuserBaseExtManageRepo extRepo;

	/**
	 * @Author: weilai
	 * @Date: 2018/9/21 12:46
	 * @parms:
	 *         <li>
	 * @desc 新增用户
	 */
	@Override
	public UserBaseExtModel addUser(UserBaseExtModel user) {
		return extRepo.save(user);
	}

	@Override
	public UserBaseModel editPwd(String username, String pwd) {
		UserBaseModel model = baseRepo.findByUsername(username);
		model.setPassword(pwd);
		return baseRepo.save(model);
	}

	@Override
	public void delUser(String username) {
		UserBaseExtModel user = this.findByUsername(username);
		this.extRepo.delete(user);
	}

	@Override
	public void delUser(List<String> ids) {
		List<UserBaseExtModel> list = this.extRepo.findAllById(ids);
		this.extRepo.deleteInBatch(list);
	}


	@Override
	public UserBaseExtModel findByUsername(String username) {

		Specification<UserBaseExtModel> specification = new Specification<UserBaseExtModel>() {
			private static final long serialVersionUID = 7840955064934055824L;

			@Override
			public Predicate toPredicate(Root<UserBaseExtModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				Join<UserBaseExtModel, UserBaseModel> join = root.join("baseuser", JoinType.INNER);
				Path<String> path = join.get("username");
				Predicate pd = criteriaBuilder.equal(path, username);
				return pd;
			}
		};

		Optional<UserBaseExtModel> ret = this.extRepo.findOne(specification);
		return ret.get();
	}

	@Override
	public void save(UserBaseExtModel newUser) {
		extRepo.save(newUser);
	}


}
