package com.lichkin.application.apis.api10001.U.n00;

import com.lichkin.framework.beans.impl.LKRequestIDBean;
import com.lichkin.framework.defines.annotations.IgnoreLog;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class I extends LKRequestIDBean {

	private String abbreviation;

	private String telephone;

	private String email;

	private String address;

	private String website;

	private String description;

	private String linkmanName;

	private String linkmanCellphone;

	@IgnoreLog
	private String photo;

}
