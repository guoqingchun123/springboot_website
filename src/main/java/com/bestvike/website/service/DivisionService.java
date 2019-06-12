package com.bestvike.website.service;

import com.bestvike.website.data.ViewDivisioninfo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface DivisionService {

	public List<ViewDivisioninfo> selectDivisions();
}