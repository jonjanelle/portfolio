package com.jjanelle.portfolio.models;

import java.util.List;

public interface ValidationCommand {
    public List<ValidationError> validate();
}
