package com.wom.ttchat.accompany.application.port.out;

import com.wom.ttchat.accompany.domain.Accompany;

public interface LoadAccompanyPort {
	Accompany loadAccompany(Long accompanyId);
}
