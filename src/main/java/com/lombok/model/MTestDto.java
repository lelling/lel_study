package com.lombok.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MTestDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4196853618667878326L;
	
	/**
	 * 编号
	 */
	private int no;
	/**
	 * 名
	 */
	private String name;
	
	public static void main(String[] args) {
		MTestDto mTestDto = new MTestDto(222, "SSSS");
		System.out.println(mTestDto.toString());
	}

}
