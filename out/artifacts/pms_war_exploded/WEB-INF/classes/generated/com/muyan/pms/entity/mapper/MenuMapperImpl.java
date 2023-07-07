package com.muyan.pms.entity.mapper;

import com.muyan.pms.entity.dto.MenuDto;
import com.muyan.pms.entity.security.Menu;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-22T22:19:27+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_333 (Oracle Corporation)"
)
@Component
public class MenuMapperImpl implements MenuMapper {

    @Override
    public Menu toEntity(MenuDto dto) {
        if ( dto == null ) {
            return null;
        }

        Menu menu = new Menu();

        menu.setId( dto.getId() );
        menu.setName( dto.getName() );
        menu.setPath( dto.getPath() );
        menu.setIcon( dto.getIcon() );
        menu.setComponent( dto.getComponent() );

        return menu;
    }

    @Override
    public MenuDto toDto(Menu entity) {
        if ( entity == null ) {
            return null;
        }

        MenuDto menuDto = new MenuDto();

        menuDto.setId( entity.getId() );
        menuDto.setName( entity.getName() );
        menuDto.setPath( entity.getPath() );
        menuDto.setComponent( entity.getComponent() );
        menuDto.setIcon( entity.getIcon() );

        return menuDto;
    }

    @Override
    public List<Menu> toEntity(List<MenuDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Menu> list = new ArrayList<Menu>( dtoList.size() );
        for ( MenuDto menuDto : dtoList ) {
            list.add( toEntity( menuDto ) );
        }

        return list;
    }

    @Override
    public List<MenuDto> toDto(List<Menu> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuDto> list = new ArrayList<MenuDto>( entityList.size() );
        for ( Menu menu : entityList ) {
            list.add( toDto( menu ) );
        }

        return list;
    }
}
