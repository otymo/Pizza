package converters

import dto.MenuDto
import dto.NumberTypeMenuDto
import dto.SumOrderDto
import dto.SumTypeMenuDto
import enums.TypeMenuItem
import enums.Unit
import org.hibernate.transform.ResultTransformer

class TransformerFactory {

    static ResultTransformer getMenuDtoResultTransformer() {
        return new ResultTransformer() {
            @Override
            Object transformTuple(Object[] tuple, String[] aliases) {
                return new MenuDto.MenuDtoBuilder()
                        .name((String) tuple[0])
                        .composition((String) tuple[1])
                        .portion((String) tuple[2])
                        .cost((BigDecimal) tuple[3])
                        .unit((Unit) tuple[4])
                        .build()
            }

            @Override
            List transformList(List collection) {
                return collection
            }
        }
    }

    static ResultTransformer getSumTypeMenuDtoResultTransformer() {
        return new ResultTransformer() {
            @Override
            Object transformTuple(Object[] tuple, String[] aliases) {
                return new SumTypeMenuDto((TypeMenuItem) tuple[0],
                        (BigDecimal) tuple[1])
            }

            @Override
            List transformList(List collection) {
                return collection
            }
        }
    }

    static ResultTransformer getNumberTypeMenuDtoResultTransformer() {
        return new ResultTransformer() {
            @Override
            Object transformTuple(Object[] tuple, String[] aliases) {
                return new NumberTypeMenuDto((TypeMenuItem) tuple[0],
                        (Integer) tuple[1])
            }

            @Override
            List transformList(List collection) {
                return collection
            }
        }
    }


    static ResultTransformer getSumOrderDtoResultTransformer() {
        return new ResultTransformer() {
            @Override
            Object transformTuple(Object[] tuple, String[] aliases) {
                return new SumOrderDto((String) tuple[0],
                        (BigDecimal) tuple[1])
            }

            @Override
            List transformList(List collection) {
                return collection
            }
        }
    }
}
