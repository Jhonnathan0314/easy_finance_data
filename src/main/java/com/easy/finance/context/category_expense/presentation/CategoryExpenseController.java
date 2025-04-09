package com.easy.finance.context.category_expense.presentation;

import com.easy.finance.context.category_expense.application.dto.CategoryExtenseCreateDto;
import com.easy.finance.context.category_expense.application.dto.CategoryExtenseResponseDto;
import com.easy.finance.context.category_expense.application.dto.CategoryExtenseUpdateDto;
import com.easy.finance.context.category_expense.application.usecase.*;
import com.easy.finance.context.category_expense.infrastructure.mapper.CategoryExpenseCreateMapper;
import com.easy.finance.context.category_expense.infrastructure.mapper.CategoryExpenseMapper;
import com.easy.finance.context.category_expense.infrastructure.mapper.CategoryExpenseUpdateMapper;
import com.easy.finance.utils.exceptions.InvalidRequestException;
import com.easy.finance.utils.exceptions.NoResultsException;
import com.easy.finance.utils.models.ApiResponse;
import com.easy.finance.utils.models.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category-expense")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryExpenseController {

    private final FindAllCategoryExpense findAllCategoryExpense;
    private final CreateCategoryExpense createCategoryExpense;
    private final UpdateCategoryExpense updateCategoryExpense;
    private final FindByIdCategoryExpense findByIdCategoryExpense;
    private final DeleteByIdCategoryExpense deleteByIdCategoryExpense;
    private final CategoryExpenseMapper categoryExpenseMapper = new CategoryExpenseMapper();
    private final CategoryExpenseCreateMapper categoryExpenseCreateMapper = new CategoryExpenseCreateMapper();
    private final CategoryExpenseUpdateMapper categoryExpenseUpdateMapper = new CategoryExpenseUpdateMapper();

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryExtenseResponseDto>>> findAll() {
        ApiResponse<List<CategoryExtenseResponseDto>> response = new ApiResponse<>();
        try {
            List<CategoryExtenseResponseDto> categoryExtenseResponseDtos = categoryExpenseMapper.modelsToDtos(findAllCategoryExpense.findAll());
            response.setData(categoryExtenseResponseDtos);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .title(e.getMessage())
                    .detail(e.getMessage())
                    .build();
            response.setError(errorResponse);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryExtenseResponseDto>> findById(@PathVariable Long id) {
        ApiResponse<CategoryExtenseResponseDto> response = new ApiResponse<>();
        try {
            CategoryExtenseResponseDto categoryExtenseResponseDto = categoryExpenseMapper.modelToDto(findByIdCategoryExpense.findById(id));
            response.setData(categoryExtenseResponseDto);
            return ResponseEntity.ok(response);
        } catch (NoResultsException e ) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.NOT_FOUND.value())
                    .title(e.getMessage())
                    .detail(e.getMessage())
                    .build();
            response.setError(errorResponse);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryExtenseResponseDto>> create(@RequestBody CategoryExtenseCreateDto categoryExtenseCreateDto) {
        ApiResponse<CategoryExtenseResponseDto> response = new ApiResponse<>();
        try {
            CategoryExtenseResponseDto categoryExtenseResponseDto = categoryExpenseMapper.modelToDto(createCategoryExpense.create(categoryExpenseCreateMapper.dtoToModel(categoryExtenseCreateDto)));
            response.setData(categoryExtenseResponseDto);
            return ResponseEntity.ok(response);
        }catch (InvalidRequestException e){
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .title(e.getMessage())
                    .detail(e.getMessage())
                    .build();
            response.setError(errorResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse<CategoryExtenseResponseDto>> update(@RequestBody CategoryExtenseUpdateDto categoryExtenseUpdateDto) {
        ApiResponse<CategoryExtenseResponseDto> response = new ApiResponse<>();
        try {
            CategoryExtenseResponseDto categoryExtenseResponseDto = categoryExpenseMapper.modelToDto(updateCategoryExpense.update(categoryExpenseUpdateMapper.dtoToModel(categoryExtenseUpdateDto)));
            response.setData(categoryExtenseResponseDto);
            return ResponseEntity.ok(response);
        } catch(InvalidRequestException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .title(e.getMessage())
                    .detail(e.getMessage())
                    .build();
            response.setError(errorResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteById(@PathVariable Long id) {
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            deleteByIdCategoryExpense.deleteById(id);
            return ResponseEntity.ok(response);
        } catch(InvalidRequestException e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .code(HttpStatus.BAD_REQUEST.value())
                    .title(e.getMessage())
                    .detail(e.getMessage())
                    .build();
            response.setError(errorResponse);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
