package com.epam.spm.dto.request;

import com.epam.spm.utils.FindParameter;
import com.epam.spm.utils.SortParameter;
import com.epam.spm.utils.SortWay;

public class CertificateFindByDTO {
    SortWay sortWay=SortWay.ASC;
    SortParameter sortParameter=SortParameter.DATE;

    FindParameter findParameter;
}
