package com.cb.sync.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class HelpdeskInfo implements Serializable {

    private Long ticketNo;

    private List<Conversations> conversations;

}
