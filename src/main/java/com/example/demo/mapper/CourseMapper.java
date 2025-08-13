package com.example.demo.mapper;

import com.examle.demo.server.dto.CourseApiDto;
import com.example.demo.entity.Course;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface CourseMapper {
  CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);
  Course toEntity(CourseApiDto courseApiDto);

  CourseApiDto toDto(Course course);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Course partialUpdate(CourseApiDto courseApiDto,
      @MappingTarget Course course);
}