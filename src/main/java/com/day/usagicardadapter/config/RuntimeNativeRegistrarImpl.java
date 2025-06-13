package com.day.usagicardadapter.config;

import com.day.usagicardadapter.model.divingfish.FishRecord;
import com.day.usagicardadapter.model.divingfish.SongBasicInfo;
import com.day.usagicardadapter.model.divingfish.SongInfo;
import com.day.usagicardadapter.model.divingfish.UserRecordInfo;
import com.day.usagicardadapter.model.uc.BestScore;
import com.day.usagicardadapter.model.uc.DifficultyCurve;
import com.day.usagicardadapter.model.uc.DifficultyInfo;
import com.day.usagicardadapter.model.uc.PlateInfo;
import com.day.usagicardadapter.model.uc.PlateScoreInfo;
import com.day.usagicardadapter.model.uc.PlateSongInfo;
import com.day.usagicardadapter.model.uc.SampleSize;
import com.day.usagicardadapter.model.uc.ScoreInfo;
import com.day.usagicardadapter.model.uc.SongDifficulties;
import com.day.usagicardadapter.model.uc.UCSongInfo;
import com.day.usagicardadapter.model.uc.UsagiCardSong;
import org.noear.solon.annotation.Component;
import org.noear.solon.aot.RuntimeNativeMetadata;
import org.noear.solon.aot.RuntimeNativeRegistrar;
import org.noear.solon.aot.hint.MemberCategory;
import org.noear.solon.core.AppContext;

@Component
public class RuntimeNativeRegistrarImpl implements RuntimeNativeRegistrar {

    private static final String MODEL_PACKAGE = "com.day.usagicardadapter.model";

    @Override
    public void register(AppContext context, RuntimeNativeMetadata metadata) {
        //divingfish
        metadata.registerReflection(FishRecord.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(SongBasicInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(SongInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(UserRecordInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        //usagi card
        metadata.registerReflection(BestScore.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(UCSongInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(PlateInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(PlateSongInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(PlateScoreInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(ScoreInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(UsagiCardSong.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(SongDifficulties.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(DifficultyInfo.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(DifficultyCurve.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);
        metadata.registerReflection(SampleSize.class, MemberCategory.INVOKE_DECLARED_METHODS,MemberCategory.INVOKE_DECLARED_CONSTRUCTORS,MemberCategory.DECLARED_FIELDS);

    }
}